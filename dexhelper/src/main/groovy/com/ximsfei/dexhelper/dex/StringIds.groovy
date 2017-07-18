package com.ximsfei.dexhelper.dex

import java.nio.ByteBuffer

class StringIds extends BaseItem {
    Map<StringIdItem, StringDataItem> stringIdsMap = new HashMap<>()

    @Override
    String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        for (idItem in stringIdsMap.keySet()) {
            stringBuilder.append(idItem.toString()).append(stringIdsMap.get(idItem))
        }
        stringBuilder.toString()
    }

    static StringIds parse(ByteBuffer dexBuffer, HeaderItem headerItem) {
        StringIds stringIds = new StringIds()
        for (i in 0..headerItem.stringIdsSize - 1) {
            StringIdItem idItem = StringIdItem.parse(dexBuffer, headerItem.stringIdsOff + i * StringIdItem.STRING_ID_ITEM)
            StringDataItem dataItem = StringDataItem.parse(dexBuffer, idItem.stringDataOff)
            stringIds.stringIdsMap.put(idItem, dataItem)
        }
        stringIds
    }

    /**
     * string_data_off uint
     */
    static class StringIdItem extends BaseItem {
        static final STRING_ID_ITEM = BaseItem.UINT
        int stringDataOff

        @Override
        String toString() {
            return "\nStringIdItem: \n[stringDataOff: " + Integer.toUnsignedString(stringDataOff) + "]\n";
        }

        static StringIdItem parse(ByteBuffer dexBuffer, int offset) {
            StringIdItem item = new StringIdItem()
            item.stringDataOff = dexBuffer.getInt(offset)
            item
        }
    }

    /**
     * utf16_size uleb128
     * data	ubyte[]
     */
    static class StringDataItem extends BaseItem {
        int utf16Size
        byte[] data

        @Override
        String toString() {
            return "\nStringDataItem: \n[utf16Size: " + Integer.toUnsignedString(utf16Size) +
                    "]\n[data: " + new String(data) +
                    "]\n";
        }

        static StringDataItem parse(ByteBuffer dexBuffer, int offset) {
            ByteBuffer data = wrapData(dexBuffer)
            StringDataItem item = new StringDataItem()
            item.utf16Size = getUleb128(data.position(offset) as ByteBuffer)
            item.data = new byte[item.utf16Size]
            data.get(item.data)
            item
        }
    }
}
