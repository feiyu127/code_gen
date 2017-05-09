<#include "fileHeaderTemplate.ftl">
package ${packageName}.service.impl;

import ${packageName}.bean.${className};
import ${packageName}.service.${className}Service;
import ${basePackageName}.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

<#include "classHeaderTemplate.ftl">
@Service("${className?uncap_first}ServiceImpl")
public class ${className}ServiceImpl extends BaseServiceImpl<${className}, ${primaryJavaType}> implements ${className}Service
{

}