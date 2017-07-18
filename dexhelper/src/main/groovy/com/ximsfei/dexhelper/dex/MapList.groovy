package com.ximsfei.dexhelper.dex

import java.nio.ByteBuffer

/**
 * size	uint
 * list	map_item[size]
 */
class MapList extends BaseItem {
    int size
    List<MapItem> mapItemList = new ArrayList<>()

    @Override
    String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        stringBuilder.append("\nMapList: \n[size: " + Integer.toUnsignedString(size) + "]\n")
        for (item in mapItemList) {
            stringBuilder.append(item.toString())
        }
        stringBuilder.toString()
    }

    static def MapList parse(ByteBuffer dexBuffer, HeaderItem headerItem) {
        ByteBuffer data = wrapData(dexBuffer)
        MapList mapList = new MapList()
        mapList.size = data.getInt(headerItem.mapOff)
        for (i in 0..mapList.size-1) {
            mapList.mapItemList.add(MapItem.parse(data, headerItem.mapOff + UINT + i * MapItem.MAP_ITEM))
        }
        mapList
    }
    /**
     * type	ushort
     * unused ushort
     * size	uint
     * offset uint
     */
    static class MapItem extends BaseItem {
        static final MAP_ITEM = BaseItem.USHORT + BaseItem.USHORT + BaseItem.UINT + BaseItem.UINT
        short type
        short unused
        int size
        int offset

        @Override
        String toString() {
            "\nMapItem: \n[type: " + Short.toUnsignedInt(type) +
                    "]\n[unused: " + Short.toUnsignedInt(unused) +
                    "]\n[size: " + Integer.toUnsignedString(size) +
                    "]\n[offset: " + Integer.toUnsignedString(offset) +
                    "]\n";
        }

        static MapItem parse(ByteBuffer dexBuffer, int offset) {
            ByteBuffer data = wrapData(dexBuffer)
            MapItem item = new MapItem()
            item.type = data.getShort(offset)
            item.unused = data.getShort(offset+BaseItem.USHORT)
            item.size = data.getInt(offset+BaseItem.USHORT * 2)
            item.offset = data.getInt(offset+BaseItem.USHORT * 2+BaseItem.UINT)
            item
        }
    }
}
