import { useState } from 'react';
import SockJS from 'sockjs-client';

export const ChatPage = () => {
  const [socket, setSocket] = useState<WebSocket | null>(null);

  const connectSocket = () => {
    if (!socket) {
      const sock = new SockJS('http://localhost:8080/path');
      setSocket(sock);

      sock.onopen = () => {
        console.log('Socket Opened');
      };

      sock.onmessage = (e) => {
        console.log('Received:', e.data);
      };

      sock.onclose = () => {
        console.log('Socket Closed');
        setSocket(null);
      };
    }
  };
  return (
    <div>
      <button onClick={connectSocket}>소켓 테스트</button>
    </div>
  );
};
