<#include "fileHeaderTemplate.ftl">
package ${packageName}.service;

import ${packageName}.bean.${className};
import ${basePackageName}.service.BaseService;

<#include "classHeaderTemplate.ftl">
public interface ${className}Service extends BaseService<${className}, ${primaryJavaType}>
{

}