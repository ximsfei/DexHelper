package com.ximsfei.dexhelper.dex

import java.nio.ByteBuffer

class TypeIds extends BaseItem {
    List<TypeIdItem> typeIdItemList = new ArrayList<>()

    @Override
    String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        for (idItem in typeIdItemList) {
            stringBuilder.append(idItem.toString())
        }
        stringBuilder.toString()
    }

    static TypeIds parse(ByteBuffer dexBuffer, HeaderItem headerItem) {
        TypeIds typeIds = new TypeIds()
        for (i in 0..headerItem.typeIdsSize - 1) {
            TypeIdItem idItem = TypeIdItem.parse(dexBuffer, headerItem.typeIdsOff + i * TypeIdItem.TYPE_ID_ITEM)
            typeIds.typeIdItemList.add(idItem)
        }
        typeIds
    }

    /**
     * descriptor_idx uint
     */
    static class TypeIdItem extends BaseItem {
        static final TYPE_ID_ITEM = BaseItem.UINT
        int descriptorIdx

        @Override
        String toString() {
            "\nTypeIdItem: \n[descriptorIdx: " + Integer.toUnsignedString(descriptorIdx) + "]\n";
        }

        static TypeIdItem parse(ByteBuffer dexBuffer, int offset) {
            TypeIdItem item = new TypeIdItem()
            item.descriptorIdx = dexBuffer.getInt(offset)
            item
        }
    }
}
