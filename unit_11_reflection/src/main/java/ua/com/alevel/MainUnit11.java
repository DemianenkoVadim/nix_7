package ua.com.alevel;

import ua.com.alevel.mapper.impl.MapperImpl;
import ua.com.alevel.parser.Impl.ParserImpl;
import ua.com.alevel.properties.Properties;

import static ua.com.alevel.util.ProgramProperties.*;

public class MainUnit11 {

    public static void main(String[] args) {
        Properties properties = new MapperImpl().mappingTheFile(Properties.class, new ParserImpl().getsProperties(args[0]));
        printsResultMessageProcessDuration(properties);
        printsResultMessageOptimisticPublication(properties);
        printsMessageDate(properties);
        printsMessageOfTheResultNotification(properties);
        printsMessageBranchProgram(properties);
    }
}
