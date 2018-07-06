package de.d3adspace.rebekah.commons.handler;

import de.d3adspace.rebekah.commons.context.RequestContext;
import de.d3adspace.rebekah.commons.packet.Packet;
import de.d3adspace.rebekah.commons.packet.io.PacketReader;
import de.d3adspace.rebekah.commons.packet.io.PacketWriter;
import de.d3adspace.rebekah.commons.request.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Felix Klauke <info@felix-klauke.de>
 */
@ExtendWith(MockitoExtension.class)
class RequestHandlerManagerImplTest {

    @Mock
    RequestHandler requestHandler;
    @Mock
    RequestContext requestContext;

    Request request;

    private RequestHandlerManager requestHandlerManager;

    @BeforeEach
    void setUp() {
        request = new TestRequest();
        requestHandlerManager = new RequestHandlerManagerImpl();
    }

    @Test
    void testRegisterPacketHandler() {
        requestHandlerManager.registerRequestHandler(new TestRequestHandler());
    }

    @Test
    void testUnregisterPacketHandlerTwiceInstance() {
        TestRequestHandler testPacketHandler = new TestRequestHandler();
        requestHandlerManager.registerRequestHandler(testPacketHandler);

        Executable executable = () -> requestHandlerManager.registerRequestHandler(testPacketHandler);
        assertThrows(IllegalStateException.class, executable);
    }

    @Test
    void testProcess() {
        requestHandlerManager.process(requestContext, request);
    }

    public static class TestRequestHandler implements RequestHandler {

        public void handleRequest(RequestContext requestContext, TestRequest testRequest) {

        }

        public void handleRequestNot0(RequestContext requestContext) {
            throw new IllegalStateException();
        }

        public void handleRequestNot1(Request request) {
            throw new IllegalStateException();
        }

        public void handleRequestNot2(TestRequest request) {
            throw new IllegalStateException();
        }

        public void handleRequestNot3(TestRequest testRequest, RequestContext requestContext) {
            throw new IllegalStateException();
        }
    }

    public static class TestRequest implements Packet {

        @Override
        public void encode(PacketWriter packetWriter) {

        }

        @Override
        public void decode(PacketReader packetReader) {

        }
    }
}