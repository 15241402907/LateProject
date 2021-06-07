package com.lgd.service;

import java.util.Map;

public interface LogService {

    Map<String, Object> queryByPage(Integer begin, Integer end);
}
