package org.dselent.scheduling.server.requests;

import org.dselent.scheduling.server.miscellaneous.RequestParameterConverter;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class CreateNotification {


    public static final RequestMethod REQUEST_TYPE = RequestMethod.POST;
    public static final String REQUEST_NAME = "createNotification";
    private static final List<HeaderKey> HEADER_KEY_LIST;
    private static final List<ParameterKey> PARAMETER_KEY_LIST;
    private static final List<BodyKey> BODY_KEY_LIST;

    public static enum HeaderKey
    {

    }

    public static enum ParameterKey
    {

    }

    public static enum BodyKey
    {
        MESSAGE,
        FROM_USER_INFO_ID,
        TO_USER_INFO_ID;
    }

    static
    {
        HEADER_KEY_LIST = new ArrayList<HeaderKey>();
        BODY_KEY_LIST = new ArrayList<BodyKey>();
        PARAMETER_KEY_LIST = new ArrayList<ParameterKey>();

        for(HeaderKey key : HeaderKey.values())
        {
            HEADER_KEY_LIST.add(key);
        }

        for(ParameterKey key : ParameterKey.values())
        {
            PARAMETER_KEY_LIST.add(key);
        }

        for(BodyKey key : BodyKey.values())
        {
            BODY_KEY_LIST.add(key);
        }

    };

    private CreateNotification() {
    }

    public static String getHeaderName(HeaderKey key)
    {
        return RequestParameterConverter.convertKeyName(key);
    }

    public static List<String> getHeaderNameList()
    {
        List<String> headerNameList = new ArrayList<>();

        for(HeaderKey key : HEADER_KEY_LIST)
        {
            headerNameList.add(getHeaderName(key));
        }

        return headerNameList;
    }

    public static String getParameterName(ParameterKey key)
    {
        return RequestParameterConverter.convertKeyName(key);
    }

    public static List<String> getParameterNameList()
    {
        List<String> parameterNameList = new ArrayList<>();

        for(ParameterKey key : PARAMETER_KEY_LIST)
        {
            parameterNameList.add(getParameterName(key));
        }

        return parameterNameList;
    }

    public static String getBodyName(BodyKey key)
    {
        return RequestParameterConverter.convertKeyName(key);
    }

    public static List<String> getBodyNameList()
    {
        List<String> bodyNameList = new ArrayList<>();

        for(BodyKey key : BODY_KEY_LIST)
        {
            bodyNameList.add(getBodyName(key));
        }

        return bodyNameList;
    }
}
