package com.ximsfei.dexhelper

import com.ximsfei.dexhelper.dex.HeaderItem
import com.ximsfei.dexhelper.dex.MapList
import com.ximsfei.dexhelper.dex.ProtoIds
import com.ximsfei.dexhelper.dex.StringIds
import com.ximsfei.dexhelper.dex.TypeIds
import com.ximsfei.dexhelper.utils.FileUtils

import java.nio.ByteBuffer
import java.nio.ByteOrder

class DexParser {
    HeaderItem headerItem
    MapList mapList
    StringIds stringIds
    TypeIds typeIds
    ProtoIds protoIds

    DexParser(File file) {
        init(FileUtils.readFile(file))
    }

    DexParser(InputStream is) {
        init(FileUtils.readInputStream(is))
    }

    def init(byte[] bytes) {
        ByteBuffer data = ByteBuffer.wrap(bytes)
        data.order(ByteOrder.LITTLE_ENDIAN);
        headerItem = HeaderItem.parse(data)
        mapList = MapList.parse(data, headerItem)
        stringIds = StringIds.parse(data, headerItem)
        typeIds = TypeIds.parse(data, headerItem)
        protoIds = ProtoIds.parse(data, headerItem)
    }
}
