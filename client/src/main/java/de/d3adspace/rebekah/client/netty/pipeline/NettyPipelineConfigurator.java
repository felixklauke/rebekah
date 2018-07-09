package de.d3adspace.rebekah.client.netty.pipeline;

import de.d3adspace.rebekah.commons.codec.PacketCodec;
import de.d3adspace.rebekah.commons.netty.pipeline.RebekahPipelineConfigurator;
import de.d3adspace.rebekah.commons.request.Request;
import de.d3adspace.rebekah.commons.response.Response;
import io.reactivex.netty.pipeline.PipelineConfigurator;

import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyPipelineConfigurator extends RebekahPipelineConfigurator implements PipelineConfigurator<Response, Request> {

    @Inject
    public NettyPipelineConfigurator(PacketCodec packetCodec) {
        super(packetCodec);
    }
}
