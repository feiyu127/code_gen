<#include "fileHeaderTemplate.ftl">
package ${packageName}.bean

import java.io.Serializable;

<#include "classHeaderTemplate.ftl">
public class ${className} implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    <#list columnList as column>
    /**
     * ${column.columnComment}
     */
    private ${column.javaType} ${column.javaName};
    
    </#list>
    <#list columnList as column>
    /**
     * 获取${column.columnComment}
     */
    public ${column.javaType} get${column.javaName?cap_first}(){
    	return this.${column.javaName};
    };
    
    /**
     * 设置${column.columnComment}
     */
    public void set${column.javaName?cap_first}(${column.javaType}, ${column.javaName}){
    	this.${column.javaName} = ${column.javaName};
    };
    
    </#list>
}