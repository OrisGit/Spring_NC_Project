package com.nc.netcracker_project.services.import_export.marshalling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nc.netcracker_project.services.import_export.EntityWrapper;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс предоставляющий методы для маршализации сущностей в файл или строку в формате JSON.
 */
public class JsonMarshaller extends AbstractMarshaller {

    /**
     * Экземпляр маршализатора.
     */
    private Gson gson;

    /**
     * Создаёт экземпляр объекта c форматируемым или не форматируемым выводом.
     * @param format устанавливает форматировать (true) или не форматировать (false) вывод.
     */
    public JsonMarshaller(boolean format) {
        super(format);
        if(format)
            gson = new GsonBuilder().setPrettyPrinting().create();
        else
            gson = new Gson();
    }


    /**
     * Маршализует сущности в файл в формате JSON. Если список пуст то файл не создаётся.
     * @param entityWrapper маршализуемый объект.
     * @param path путь к файлу.
     * @throws MarshallingException ошибка маршализации.
     */
    @Override
    public void exportToFile(String path, EntityWrapper entityWrapper) throws MarshallingException {
        if(entityWrapper!=null){
            try {
                FileWriter fileWriter = new FileWriter(path);
                gson.toJson(entityWrapper, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                logger.warning("Ошибка маршализации: "+e);
                throw new MarshallingException("Ошибка маршализации: ",e);
            }
        }
    }

    /**
     * Экспортирует список сущностей в строку в формате JSON. Если список пуст то возвращает null;
     * @param entityWrapper экспортируемый объект.
     * @return возвращает строку в формате JSON или null если список пуст.
     */
    @Override
    public String exportToString(EntityWrapper entityWrapper){
        if(entityWrapper!=null)
            return gson.toJson(entityWrapper);
        return null;
    }
}
