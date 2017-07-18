package com.ximsfei.dexhelper.dex

import java.nio.ByteBuffer

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
class HeaderItem extends BaseItem {
    static final int MAGIC = UBYTE * 8;
    static final int SIGNATURE = UBYTE * 20;
    static final int CHECKSUM = UINT;
    static final int HEADER_ITEM = MAGIC + CHECKSUM + SIGNATURE + (20 * UINT); // 0x70

    byte[] magic
    int checksum
    byte[] signature
    int fileSize
    int headerSize
    int endianTag
    int linkSize
    int linkOff
    int mapOff
    int stringIdsSize
    int stringIdsOff
    int typeIdsSize
    int typeIdsOff
    int protoIdsSize
    int protoIdsOff
    int fieldIdsSize
    int fieldIdsOff
    int methodIdsSize
    int methodIdsOff
    int classDefsSize
    int classDefsOff
    int dataSize
    int dataOff

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
        ByteBuffer data = wrapData(dexBuffer)
        HeaderItem item = new HeaderItem()
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
