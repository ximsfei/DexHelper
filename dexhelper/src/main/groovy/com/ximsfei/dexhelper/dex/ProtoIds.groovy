package com.ximsfei.dexhelper.dex

import java.nio.ByteBuffer

class ProtoIds extends BaseItem {
    List<ProtoIdItem> protoIdItemList = new ArrayList<>()

    @Override
    String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        for (idItem in protoIdItemList) {
            stringBuilder.append(idItem.toString())
        }
        stringBuilder.toString()
    }

    static ProtoIds parse(ByteBuffer dexBuffer, HeaderItem headerItem) {
        ProtoIds typeIds = new ProtoIds()
        for (i in 0..headerItem.protoIdsSize - 1) {
            ProtoIdItem idItem = ProtoIdItem.parse(dexBuffer, headerItem.protoIdsOff + i * ProtoIdItem.PROTO_ID_ITEM)
            typeIds.protoIdItemList.add(idItem)
        }
        typeIds
    }

    /**
     * shorty_idx uint
     * return_type_idx uint
     * parameters_off uint
     */
    static class ProtoIdItem extends BaseItem {
        static final PROTO_ID_ITEM = BaseItem.UINT * 3
        int shortyIdx
        int returnTypeIdx
        int parametersOff

        @Override
        String toString() {
            return "\nProtoIdItem: \n[shortyIdx: " + Integer.toUnsignedString(shortyIdx) +
                    "]\n[returnTypeIdx: " + Integer.toUnsignedString(returnTypeIdx) +
                    "]\n[parametersOff: " + Integer.toUnsignedString(parametersOff) +
                    "]\n";
        }

        static ProtoIdItem parse(ByteBuffer dexBuffer, int offset) {
            ProtoIdItem item = new ProtoIdItem()
            dexBuffer.position(offset)
            item.shortyIdx = dexBuffer.getInt()
            item.returnTypeIdx = dexBuffer.getInt()
            item.parametersOff = dexBuffer.getInt()
            item
        }
    }
}
