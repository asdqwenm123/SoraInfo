package io.github.asdqwenm123.velocity.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ByteSerializer {
    private ByteArrayDataOutput byteArray;
    public ByteSerializer(String... content) {
        ByteArrayDataOutput byteArray = ByteStreams.newDataOutput();
        for (String s : content) {
            byteArray.writeUTF(s);
        }
        this.byteArray = byteArray;
    }

    public byte[] toArray() {
        return byteArray.toByteArray();
    }
}
