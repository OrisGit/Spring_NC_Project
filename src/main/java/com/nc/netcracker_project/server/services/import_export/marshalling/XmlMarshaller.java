package com.nc.netcracker_project.server.services.import_export.marshalling;


import com.nc.netcracker_project.server.services.import_export.EntityWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;

/**
 * Класс предоставляющий методы для маршализации сущностей в файл или строку в формате XML.
 */
public class XmlMarshaller extends AbstractMarshaller {

    /**
     * Экземпляр маршализатора.
     */
    private Marshaller marshaller;

    /**
     * Создаёт экземпляр объекта c форматируемым или не форматируемым выводом.
     *
     * @param format устанавливает форматировать (true) или не форматировать (false) вывод.
     */
    public XmlMarshaller(boolean format) {
        super(format);
    }

    /**
     * Маршализует сущности в файл в формате XML. Если список пуст то файл не создаётся.
     * @param entityWrapper экспортируемый объект.
     * @param path путь к файлу.
     * @throws MarshallingException Ошибка маршализации.
     */
    @Override
    public void exportToFile(String path, EntityWrapper entityWrapper) throws MarshallingException {
        if(entityWrapper!=null){
            File file = new File(path);
            try {
                getMarshaller().marshal(entityWrapper,file);
            } catch (JAXBException e) {
                logger.warning("Ошибка маршализации: "+e);
                throw new MarshallingException("Ошибка маршализации: ",e);
            }
        }
    }

    /**
     * Маршализует сущности в строку в формате XML. Если список пуст то возвращает null;
     * @param entityWrapper экспортируемый объект.
     * @return возвращает строку в формате JSON или null если список пуст.
     * @exception MarshallingException Ошибка маршализации.
     */
    @Override
    public String exportToString(EntityWrapper entityWrapper) throws MarshallingException {
        if(entityWrapper!=null){
            StringWriter sw = new StringWriter();
            try {
                getMarshaller().marshal(entityWrapper,sw);
                return sw.toString();
            } catch (JAXBException e) {
                logger.warning("Ошибка маршализации: "+e);
                throw new MarshallingException("Ошибка маршализации: ",e);
            }
        }
        return null;
    }

    /**
     * Создаёт (если еще не создан) и возвращает Marshaller применяя к нему настроийки в соответствии с
     * переданными в конструкторе параметрами.
     * @return маршализатор с установленными параметрами.
     * @throws MarshallingException ошибка экспорта.
     */
    private Marshaller getMarshaller() throws MarshallingException {
        if(marshaller==null){
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(EntityWrapper.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                if(format)
                    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
                marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                return this.marshaller = marshaller;
            } catch (JAXBException e) {
                logger.warning("Ошибка инициализации маршализатора: "+e.getMessage());
                throw new MarshallingException("Ошибка инициализации маршализатора: ", e);
            }
        }
        return marshaller;
    }
}
