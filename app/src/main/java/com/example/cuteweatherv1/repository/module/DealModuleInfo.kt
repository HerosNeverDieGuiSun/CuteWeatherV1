package com.example.cuteweatherv1.repository.module

/**
 * Created by 胡婵娟.
 * 内容：处理模块管理页面数据
 */
class DealModuleInfo {
    fun changeIsOpen(position: Int): Boolean {
        var result = true
        if (ModuleRepository.instance.data[position].isOpen) {
            result = false
        } else {
            result = true
        }
        return result
    }
}