<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.dao.${className}Mapper">

	<resultMap type="${className?uncap_first}"  id="${className?uncap_first}ResultMap">
		<#list columnList as column >
		<${("PRI" == column.columnKey)? string('id','result')} column="${column.columnName}" property="${column.javaName}" />
		</#list>
	</resultMap>
	
	<insert id="saveEntity" parameterType="${className?uncap_first}" <#if "auto_increment" == primaryExtra >keyProperty="${primaryJavaName}" useGeneratedKeys="true"</#if>>
	INSERT INTO ${tableName}(
	<#list columnList as column >
		<#if "PRI" != column.columnKey || "auto_increment" != column.extra>
		${column.columnName}<#if column_has_next>,</#if>
		</#if>
	</#list>
	) values(
	<#list columnList as column >
		<#if "PRI" != column.columnKey || "auto_increment" != column.extra>
		${r'#{'}${column.javaName}}<#if column_has_next>,</#if>
		</#if>
	</#list>
	)
	</insert>
	
	<insert id="batchSaveEntity" <#if "auto_increment" == primaryExtra >keyProperty="${primaryJavaName}" useGeneratedKeys="true"</#if>>
	INSERT INTO ${tableName}(
	<#list columnList as column >
		<#if "PRI" != column.columnKey || "auto_increment" != column.extra>
		${column.columnName}<#if column_has_next>,</#if>
		</#if>
	</#list>
	) values
	<foreach collection="list" item="item" separator=",">
	(
	<#list columnList as column >
		<#if "PRI" != column.columnKey || "auto_increment" != column.extra>
		${r'#{'}item.${column.javaName}}<#if column_has_next>,</#if>
		</#if>
	</#list>
	)
	</foreach>
	</insert>
	
	
	<select id="getEntityByKey" parameterType="${primaryJavaType}" resultMap="${className?uncap_first}ResultMap">
		SELECT
		<#list columnList as column >
		<#if "PRI" != column.columnKey || "auto_increment" != column.extra>
		${column.columnName}<#if column_has_next>,</#if>
		</#if>
		</#list>
		FROM `${tableName}`
		WHERE ${primaryColumnName} = ${r'#{'}_parameter}
	</select>
	
	
</mapper>