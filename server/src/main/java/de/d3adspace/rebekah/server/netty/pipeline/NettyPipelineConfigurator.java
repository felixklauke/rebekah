package de.d3adspace.rebekah.server.netty.pipeline;

import de.d3adspace.rebekah.commons.codec.PacketCodec;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.reactivex.netty.pipeline.PipelineConfigurator;

import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyPipelineConfigurator implements PipelineConfigurator<Request, Response> {

    /**
     * The packet codec responsible for encoding and decoding packets.
     */
    private final PacketCodec packetCodec;

    /**
     * Create a new pipeline configurator based of the internal packet codec.
     *
     * @param packetCodec The packet codec.
     */
    @Inject
    public NettyPipelineConfigurator(PacketCodec packetCodec) {
        this.packetCodec = packetCodec;
    }

    @Override
    public void configureNewPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4));
        pipeline.addLast("packetCodec", packetCodec);
        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(4));
    }
}
