package de.d3adspace.rebekah.server.netty.pipeline;

import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.reactivex.netty.pipeline.PipelineConfigurator;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyPipelineConfigurator implements PipelineConfigurator<Request, Response> {

    @Override
    public void configureNewPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4));
        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(4));
    }
}
