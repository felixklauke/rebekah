package de.d3adspace.rebekah.server.netty.pipeline;

import de.d3adspace.rebekah.commons.codec.PacketCodec;
import de.d3adspace.rebekah.commons.message.IncomingMessage;
import de.d3adspace.rebekah.commons.message.OutgoingMessage;
import de.d3adspace.rebekah.commons.netty.pipeline.RebekahPipelineConfigurator;
import io.netty.channel.ChannelPipeline;
import io.reactivex.netty.pipeline.PipelineConfigurator;
import javax.inject.Inject;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
public class NettyPipelineConfigurator extends
  RebekahPipelineConfigurator implements
  PipelineConfigurator<IncomingMessage, OutgoingMessage> {

  @Inject
  public NettyPipelineConfigurator(PacketCodec packetCodec) {
    super(packetCodec);
  }

  @Override
  public void configureNewPipeline(ChannelPipeline pipeline) {
    super.configureNewPipeline(pipeline);
  }
}
