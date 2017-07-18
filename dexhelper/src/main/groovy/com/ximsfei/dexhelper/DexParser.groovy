package com.ximsfei.dexhelper

import com.ximsfei.dexhelper.dex.HeaderItem
import com.ximsfei.dexhelper.dex.MapList
import com.ximsfei.dexhelper.dex.StringIds
import com.ximsfei.dexhelper.dex.TypeIds
import com.ximsfei.dexhelper.utils.FileUtils
import com.ximsfei.dexhelper.utils.Log

import java.nio.ByteBuffer
import java.nio.ByteOrder

class DexParser {
    static def parse(File dexFile) {
        ByteBuffer data = ByteBuffer.wrap(FileUtils.readFile(dexFile))
        data.order(ByteOrder.LITTLE_ENDIAN);
        HeaderItem headerItem = HeaderItem.parse(data)
        Log.d("headerItem", headerItem.toString())
        MapList mapList = MapList.parse(data, headerItem)
        Log.d("mapList", mapList.toString())
        StringIds stringIds = StringIds.parse(data, headerItem)
        Log.d("stringIds", stringIds.toString())
        TypeIds typeIds = TypeIds.parse(data, headerItem)
        Log.d("typeIds", typeIds.toString())
    }
}
