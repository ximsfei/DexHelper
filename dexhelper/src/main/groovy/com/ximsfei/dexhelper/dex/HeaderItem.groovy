package com.ximsfei.dexhelper.dex

import java.nio.ByteBuffer
import java.nio.ByteOrder

import static com.ximsfei.dexhelper.utils.Size.*

/**
 * magic ubyte[8]
 * checksum uint
 * signature ubyte[20]
 * file_size uint
 * header_size uint
 * endian_tag uint
 * link_size uint
 * link_off uint
 * map_off uint
 * string_ids_size uint
 * string_ids_off uint
 * type_ids_size uint
 * type_ids_off uint
 * proto_ids_size uint
 * proto_ids_off uint
 * field_ids_size uint
 * field_ids_off uint
 * method_ids_size uint
 * method_ids_off uint
 * class_defs_size uint
 * class_defs_off uint
 * data_size uint
 * data_off uint
 */
class HeaderItem {
    static final int MAGIC = UBYTE * 8;
    static final int SIGNATURE = UBYTE * 20;
    static final int CHECKSUM = UINT;
    static final int HEADER_ITEM = MAGIC + CHECKSUM + SIGNATURE + (20 * UINT); // 0x70

    private byte[] magic
    private int checksum
    private byte[] signature
    private int fileSize
    private int headerSize
    private int endianTag
    private int linkSize
    private int linkOff
    private int mapOff
    private int stringIdsSize
    private int stringIdsOff
    private int typeIdsSize
    private int typeIdsOff
    private int protoIdsSize
    private int protoIdsOff
    private int fieldIdsSize
    private int fieldIdsOff
    private int methodIdsSize
    private int methodIdsOff
    private int classDefsSize
    private int classDefsOff
    private int dataSize
    private int dataOff

    byte[] getMagic() {
        return magic
    }

    int getChecksum() {
        return checksum
    }

    byte[] getSignature() {
        return signature
    }

    int getFileSize() {
        return fileSize
    }

    int getHeaderSize() {
        return headerSize
    }

    int getEndianTag() {
        return endianTag
    }

    int getLinkSize() {
        return linkSize
    }

    int getLinkOff() {
        return linkOff
    }

    int getMapOff() {
        return mapOff
    }

    int getStringIdsSize() {
        return stringIdsSize
    }

    int getStringIdsOff() {
        return stringIdsOff
    }

    int getTypeIdsSize() {
        return typeIdsSize
    }

    int getTypeIdsOff() {
        return typeIdsOff
    }

    int getProtoIdsSize() {
        return protoIdsSize
    }

    int getProtoIdsOff() {
        return protoIdsOff
    }

    int getFieldIdsSize() {
        return fieldIdsSize
    }

    int getFieldIdsOff() {
        return fieldIdsOff
    }

    int getMethodIdsSize() {
        return methodIdsSize
    }

    int getMethodIdsOff() {
        return methodIdsOff
    }

    int getClassDefsSize() {
        return classDefsSize
    }

    int getClassDefsOff() {
        return classDefsOff
    }

    int getDataSize() {
        return dataSize
    }

    int getDataOff() {
        return dataOff
    }

    @Override
    String toString() {
        return "\nHeaderItem: \n[magic: " + new String(magic).trim() +
                "]\n[checksum: " + Integer.toUnsignedString(checksum) +
                "]\n[signature: " + signature.inspect() +
                "]\n[fileSize: " + Integer.toUnsignedString(fileSize) +
                "]\n[headerSize: " + headerSize +
                "]\n[endianTag: " + Integer.toUnsignedString(endianTag) +
                "]\n[linkSize: " + Integer.toUnsignedString(linkSize) +
                "]\n[linkOff: " + Integer.toUnsignedString(linkOff) +
                "]\n[mapOff: " + Integer.toUnsignedString(mapOff) +
                "]\n[stringIdsSize: " + Integer.toUnsignedString(stringIdsSize) +
                "]\n[stringIdsOff: " + Integer.toUnsignedString(stringIdsOff) +
                "]\n[typeIdsSize: " + Integer.toUnsignedString(typeIdsSize) +
                "]\n[typeIdsOff: " + Integer.toUnsignedString(typeIdsOff) +
                "]\n[protoIdsSize: " + Integer.toUnsignedString(protoIdsSize) +
                "]\n[protoIdsOff: " + Integer.toUnsignedString(protoIdsOff) +
                "]\n[fieldIdsSize: " + Integer.toUnsignedString(fieldIdsSize) +
                "]\n[fieldIdsOff: " + Integer.toUnsignedString(fieldIdsOff) +
                "]\n[methodIdsSize: " + Integer.toUnsignedString(methodIdsSize) +
                "]\n[methodIdsOff: " + Integer.toUnsignedString(methodIdsOff) +
                "]\n[classDefsSize: " + Integer.toUnsignedString(classDefsSize) +
                "]\n[classDefsOff: " + Integer.toUnsignedString(classDefsOff) +
                "]\n[dataSize: " + Integer.toUnsignedString(dataSize) +
                "]\n[dataOff: " + Integer.toUnsignedString(dataOff) +
                "]\n";
    }

    static def HeaderItem parse(ByteBuffer dexBuffer) {
        HeaderItem item = new HeaderItem()
        ByteBuffer data = dexBuffer.duplicate()
        data.order(ByteOrder.LITTLE_ENDIAN);
        item.magic = new byte[MAGIC]
        data.get(item.magic)
        item.checksum = data.getInt()
        item.signature = new byte[SIGNATURE]
        data.get(item.signature)
        item.fileSize = data.getInt()
        item.headerSize = data.getInt()
        item.endianTag = data.getInt()
        item.linkSize = data.getInt()
        item.linkOff = data.getInt()
        item.mapOff = data.getInt()
        item.stringIdsSize = data.getInt()
        item.stringIdsOff = data.getInt()
        item.typeIdsSize = data.getInt()
        item.typeIdsOff = data.getInt()
        item.protoIdsSize = data.getInt()
        item.protoIdsOff = data.getInt()
        item.fieldIdsSize = data.getInt()
        item.fieldIdsOff = data.getInt()
        item.methodIdsSize = data.getInt()
        item.methodIdsOff = data.getInt()
        item.classDefsSize = data.getInt()
        item.classDefsOff = data.getInt()
        item.dataSize = data.getInt()
        item.dataOff = data.getInt()
        item
    }
}
