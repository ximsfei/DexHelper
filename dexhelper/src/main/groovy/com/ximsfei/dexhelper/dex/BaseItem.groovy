package com.ximsfei.dexhelper.dex

import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Created by ximsfei on 2017/7/18.
 */
class BaseItem {
    static final int UBYTE = 1;
    static final int USHORT = 2;
    static final int UINT = 4;

    static ByteBuffer wrapData(ByteBuffer dexBuffer) {
        ByteBuffer data = dexBuffer.duplicate()
        data.order(ByteOrder.LITTLE_ENDIAN);
        data
    }

    static int getUleb128(ByteBuffer dexBuffer) {
        int result = 0;
        int cur;
        int count = 0;

        cur = dexBuffer.get() & 0xff
        result |= (cur & 0x7f) << (count * 7)
        count++
        while (((cur & 0x80) == 0x80) && count < 5) {
            cur = dexBuffer.get() & 0xff
            result |= (cur & 0x7f) << (count * 7)
            count++
        }

        if ((cur & 0x80) == 0x80) {
            throw new RuntimeException("invalid LEB128 sequence");
        }

        result;
    }
}
