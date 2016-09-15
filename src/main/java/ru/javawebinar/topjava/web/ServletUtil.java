package ru.javawebinar.topjava.web;// Created by Neginskiy Gregoriy.

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServletUtil {
    public static void setEncoding(HttpServletRequest request, HttpServletResponse response, String encoding) throws UnsupportedEncodingException {
        response.setCharacterEncoding(encoding);
        request.setCharacterEncoding(encoding);
    }

    public static String getOptionalParam(String paramName, String defaultValue, HttpServletRequest request) {
        String value = request.getParameter(paramName);
        return ServletUtil.isEmpty(value)?defaultValue:value;
    }

    public static Integer getOptionalParam(String paramName, int defaultValue, HttpServletRequest request) {
        String value = request.getParameter(paramName);
        return ServletUtil.isEmpty(value)?defaultValue:Integer.parseInt(request.getParameter(paramName));
    }

    private static boolean isEmpty(String value) {
        return value==null||value.trim().isEmpty();
    }

    public static LocalDateTime getOptionalParam(String paramName, LocalDateTime defaultValue, DateTimeFormatter formatter, HttpServletRequest request) {
        String value = request.getParameter(paramName);
        return ServletUtil.isEmpty(value)?defaultValue:LocalDateTime.parse(request.getParameter("dateTime"), formatter);
    }

    public static boolean getOptionalParam(String paramName, boolean defaultValue, HttpServletRequest request) {
        return Boolean.valueOf(request.getParameter(paramName))|defaultValue;
    }
}