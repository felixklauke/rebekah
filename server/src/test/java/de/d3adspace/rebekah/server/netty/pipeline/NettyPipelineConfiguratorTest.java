package de.d3adspace.rebekah.server.netty.pipeline;

import de.d3adspace.rebekah.commons.codec.PacketCodec;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class NettyPipelineConfiguratorTest {

    @Mock
    PacketCodec packetCodec;
    @Mock
    ChannelPipeline channelPipeline;

    private NettyPipelineConfigurator nettyPipelineConfigurator;

    @BeforeEach
    void setUp() {
        nettyPipelineConfigurator = new NettyPipelineConfigurator(packetCodec);
    }

    @Test
    void testConfigureNewPipeline() {
        nettyPipelineConfigurator.configureNewPipeline(channelPipeline);

        verify(channelPipeline).addLast(eq("lengthFieldBasedFrameDecoder"), Mockito.any(LengthFieldBasedFrameDecoder.class));
        verify(channelPipeline).addLast("packetCodec", packetCodec);
        verify(channelPipeline).addLast(eq("lengthFieldPrepender"), Mockito.any(LengthFieldPrepender.class));
    }
}