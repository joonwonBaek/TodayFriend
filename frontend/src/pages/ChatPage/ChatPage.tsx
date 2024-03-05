import { Client } from '@stomp/stompjs';
import { useRef } from 'react';
import SockJS from 'sockjs-client';

export const ChatPage = () => {
  const stompClient = useRef<Client>(null);
  const client = new Client({
    webSocketFactory: () => new SockJS('http://localhost:8080/ws'),

    onConnect: () => {
      console.log('Connected to WebSocket');
    },

    onDisconnect: () => {
      console.log('Disconnected from WebSocket');
    },

    onStompError: (err) => {
      console.log(err);
    },
  });

  const onConnected = () => {
    client.subscribe(
      `/from/game-room/${roomId}`,
      (e) => onMessageReceived(e),
      connectHeaders,
    );
    client.subscribe(
      `/from/game-room/${roomId}/error`,
      // eslint-disable-next-line no-console
      (e) => console.log('----subscribe error----', e),
      connectHeaders,
    );
  };

  const onDisconnected = (roomId: number) => {
    client.publish({
      destination: `/to/game-room/${roomId}`,
      headers: connectHeaders,
    });
  };

  // const onMessageReceived = ({ body }: { body: string }) => {
  //   const responsePublish = JSON.parse(body);
  //   setGameRoomRes(responsePublish);
  //   if (checkIsEmptyObj(responsePublish)) {
  //     setIsWsError(true);
  //   }
  // };

  const handleEnterGameRoom = (roomId: number) => {
    client.publish({
      destination: `/to/game-room/${roomId}/enter`,
      headers: connectHeaders,
    });
  };

  client.activate();
  // stompClient.current = client;
  // return () => {
  //   client.deactivate();
  // };
  return (
    <div>
      <button onClick={onConnected}>소켓 테스트</button>
    </div>
  );
};
