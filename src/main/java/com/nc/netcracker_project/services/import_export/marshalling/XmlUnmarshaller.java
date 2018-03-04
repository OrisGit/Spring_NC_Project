package com.nc.netcracker_project.services.import_export.marshalling;

import com.nc.netcracker_project.services.import_export.EntityWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

/**
 * Класс-менеджер предоставляющий методы для демаршализации сущностей из файла или строки в формате XML.
 */
public class XmlUnmarshaller extends AbstractUnmarshaller {

    /**
     * Экземпляр демаршализатора.
     */
    private Unmarshaller unmarshaller;

    /**
     * Демаршализует сущности из файла в формате XML.
     * @param path путь к файлу.
     * @throws UnmarshallingException ошибка демаршализации.
     */
    @Override
    public EntityWrapper importFromFile(String path) throws UnmarshallingException {
        StreamSource streamSource = new StreamSource(path);
        JAXBElement<EntityWrapper> jaxbElement;
        try {
            jaxbElement = getUnmarshaller().unmarshal(streamSource, EntityWrapper.class);
        } catch (JAXBException e) {
            logger.warning("Ошибка демаршализации: "+e);
            throw new UnmarshallingException("Ошибка демаршализации: ",e);
        }
        return jaxbElement.getValue();
    }


    /**
     * Демаршализует сущности из строки в формате XML.
     * @param xmlString строка в формате XML.
     * @throws UnmarshallingException ошибка демаршализации.
     */
    @Override
    public EntityWrapper importFromString(String xmlString) throws UnmarshallingException {
        StreamSource streamSource = new StreamSource(new ByteArrayInputStream(xmlString.getBytes(Charset.defaultCharset())));
        JAXBElement<EntityWrapper> jaxbElement;
        try {
            jaxbElement = getUnmarshaller().unmarshal(streamSource, EntityWrapper.class);
        } catch (JAXBException e) {
            logger.warning("Ошибка демаршализации: "+e);
            throw new UnmarshallingException("Ошибка демаршализации: ",e);
        }
        return jaxbElement.getValue();
    }


    /**
     * Создаёт (если еще не создан) и возвращает Unmarshaller применяя к нему настроийки в соответствии с
     * переданными в конструкторе параметрами.
     * @return демаршализатор с установленными параметрами.
     * @throws UnmarshallingException ошибка демаршализации.
     */
    private Unmarshaller getUnmarshaller() throws UnmarshallingException {
        if(unmarshaller==null){
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(EntityWrapper.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                return this.unmarshaller = unmarshaller;
            } catch (JAXBException e) {
                logger.warning("Ошибка инициализации XML демаршализатора: "+e.getMessage());
                throw new UnmarshallingException("Ошибка инициализации XML демаршализатора.", e);
            }
        }
        return unmarshaller;
    }
}
