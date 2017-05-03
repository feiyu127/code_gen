<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.${className}Mapper">

	<resultMap type="${className?uncap_first}"  id="${className?uncap_first}ResultMap">
		<#list columnList as column >
		<${("PRI" == column.columnKey)? string('id','result')} column="${column.columnName}" property="${column.javaName}" />
		</#list>
	</resultMap>
	
	<insert id="saveEntity" parameterType="${className?uncap_first}">
	INSERT INTO ${tableName}(
		<#list columnList as column >
		<#if "PRI" != column.columnKey>
			${column.columnName}
		</#if>
		<${("PRI" == column.columnKey)? string('id','result')} column="${column.columnName}" property="${column.javaName}" />
		</#list>
	)
	</insert>
</mapper>