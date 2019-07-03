package com.suirui.drouter.compiler.utils;

import com.squareup.javapoet.ClassName;

/**
 * @author  cui.li by 2019/06/14
 * Description:
 */

public class Constant {
   public static final String packageName="com.suirui.drouter.core";
   public static final String anntationPack="com.suirui.drouter.annotation";

    public static final ClassName ROUTER = ClassName.get(packageName, "zouter");
    public static final String ACTIVITY = "android.app.Activity";
    public static final String ISERVICE = packageName+".template.IService";
    public static final String ARGUMENTS_NAME = "moduleName";
    public static final String ANNOTATION_TYPE_ROUTE = anntationPack+".Route";
    public static final String ANN_TYPE_EXTRA = anntationPack+".Extra";
    public static final String IROUTE_GROUP = packageName+".template.IRouteGroup";
    public static final String IROUTE_ROOT = packageName+".template.IRouteRoot";
    public static final String IEXTRA = packageName+".template.IExtra";
    public static final String SEPARATOR = "_";
    public static final String PROJECT = "ZRouter";
    public static final String NAME_OF_GROUP = PROJECT + SEPARATOR + "Group" + SEPARATOR;
    public static final String NAME_OF_ROOT = PROJECT + SEPARATOR + "Root" + SEPARATOR;
    public static final String PACKAGE_OF_GENERATE_FILE = "com.suirui.zrouter.routes";

    public static final String METHOD_LOAD_INTO = "loadInto";
    public static final String METHOD_LOAD_EXTRA = "loadExtra";

    public static final String PARCELABLE = "android.os.Parcelable";

    private static final String LANG = "java.lang";
    public static final String BYTE = LANG + ".Byte";
    public static final String SHORT = LANG + ".Short";
    public static final String INTEGER = LANG + ".Integer";
    public static final String LONG = LANG + ".Long";
    public static final String FLOAT = LANG + ".Float";
    public static final String DOUBEL = LANG + ".Double";
    public static final String BOOLEAN = LANG + ".Boolean";
    public static final String STRING = LANG + ".String";
    public static final String ARRAY = "ARRAY";

    public static final String ARRAYLIST = "java.util.ArrayList";
    public static final String LIST = "java.util.List";

    public static final String BYTEARRAY = "byte[]";
    public static final String SHORTARRAY = "short[]";
    public static final String BOOLEANARRAY = "boolean[]";
    public static final String CHARARRAY = "char[]";
    public static final String DOUBLEARRAY = "double[]";
    public static final String FLOATARRAY = "float[]";
    public static final String INTARRAY = "int[]";
    public static final String LONGARRAY = "long[]";
    public static final String STRINGARRAY = "java.lang.String[]";


    public static final String NAME_OF_EXTRA = SEPARATOR + "Extra";


}
