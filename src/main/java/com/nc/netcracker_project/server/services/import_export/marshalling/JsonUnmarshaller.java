package com.nc.netcracker_project.server.services.import_export.marshalling;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.nc.netcracker_project.server.services.import_export.EntityWrapper;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Класс предоставляющий методы для демарщализации сущностей из файла или строки в формате JSON.
 */
public class JsonUnmarshaller extends AbstractUnmarshaller {

    /**
     * Экземпляр демаршализатора.
     */
    private Gson gson;

    public JsonUnmarshaller() {
        gson = new Gson();
    }

    /**
     * Демарщализует список сущностей из файла в формате JSON.
     * @param path путь к файлу.
     * @throws UnmarshallingException ошибка демарщализации.
     */
    @Override
    public EntityWrapper importFromFile(String path) throws UnmarshallingException {
        EntityWrapper entityWrapper;
        try{
            entityWrapper = gson.fromJson(new FileReader(path),EntityWrapper.class);
        }catch (JsonSyntaxException e){
            logger.warning("Не возможно выполнить демарщализацию из JSON файла т.к. информация в файле не соответствует формату JSON: "+e);
            throw new UnmarshallingException("Не возможно выполнить импорт из JSON файла т.к. информация в файле не соответствует формату JSON.",e);
        }catch (JsonIOException e){
            logger.warning("Не возможно выполнить демарщализацию из JSON файла из-за ошибки ввода-вывода: "+e);
            throw new UnmarshallingException("Не возможно выполнить импорт из JSON файла из-за ошибки ввода-вывода.",e);
        } catch (FileNotFoundException e) {
            logger.warning(String.format("Не возможно выполнить демарщализацию из JSON файла т.к. файл по пути: %s не найден: ", path)+e);
            throw new UnmarshallingException(String.format("Не возможно выполнить импорт из JSON файла т.к. файл по пути: %s не найден", path),e);
        }
        return entityWrapper;
    }

    /**
     * Демарщализует список сущностей из строки в формате JSON.
     * @param jsonString строка в формате JSON.
     * @throws UnmarshallingException ошибка демарщализации.
     */
    @Override
    public EntityWrapper importFromString(String jsonString) throws UnmarshallingException {
        EntityWrapper entityWrapper;
        try{
            entityWrapper = gson.fromJson(jsonString, EntityWrapper.class);
        }catch (JsonSyntaxException e){
            logger.warning("Не возможно выполнить демарщализацию из JSON строки т.к. информация в файле не соответствует формату JSON: "+e);
            throw new UnmarshallingException("Не возможно выполнить демарщализацию из JSON строки т.к. информация в файле не соответствует формату JSON.",e);
        }
        return entityWrapper;
    }
}
