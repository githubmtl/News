package com.program.haohu.service.admin;

import com.program.haohu.entity.admin.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 日志接口
 *
 * @author hh
 *
 */
@Service
public interface LogService {
	public int add(Log log);
	public int add(String content);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}
