<#include "fileHeaderTemplate.ftl">
package ${packageName}.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ${packageName}.bean.${className};
import ${basePackageName}.controller.BaseController;

<#include "classHeaderTemplate.ftl">
@Controller
@RequestMapping("${className?uncap_first}")
public class ${className}Controller extends BaseController<${className}>
{

}