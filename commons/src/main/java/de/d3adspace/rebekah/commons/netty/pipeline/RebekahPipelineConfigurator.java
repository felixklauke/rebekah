package de.d3adspace.rebekah.commons.netty.pipeline;

import de.d3adspace.rebekah.commons.codec.PacketCodec;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class RebekahPipelineConfigurator {

    /**
     * The packet codec responsible for encoding and decoding packets.
     */
    private final PacketCodec packetCodec;

    /**
     * Create a new pipeline configurator based of the internal packet codec.
     *
     * @param packetCodec The packet codec.
     */
    public RebekahPipelineConfigurator(PacketCodec packetCodec) {
        this.packetCodec = packetCodec;
    }

    public void configureNewPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4));
        pipeline.addLast("packetCodec", packetCodec);
        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(4));
    }
}
