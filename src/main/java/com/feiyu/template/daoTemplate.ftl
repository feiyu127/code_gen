<#include "fileHeaderTemplate.ftl">
package ${packageName}.dao

import ${packageName}.bean.${className};
import ${basePackageName}.dao.BaseMapper;

<#include "classHeaderTemplate.ftl">
public interface ${className}Mapper extends BaseMapper<${className}>
{

}