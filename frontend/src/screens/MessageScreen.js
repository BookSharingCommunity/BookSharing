import React, { useState, useEffect } from 'react';
import axios from 'axios';

function MessageScreen({ userId }) {
  const [threads, setThreads] = useState([]);
  const [selectedThreadId, setSelectedThreadId] = useState(null);
  const [content, setContent] = useState('');

  useEffect(() => {
    // fetch all threads related to the user
    axios.get(`/api/threads?userId=${userId}`)
      .then(response => {
        setThreads(response.data);
        // select the first thread by default
        if (response.data.length > 0) {
          setSelectedThreadId(response.data[0].id);
        }
      })
      .catch(error => {
        console.log(error);
      });
  }, [userId]);

  const sendMessage = () => {
    const data = {
      senderId: userId,
      threadId: selectedThreadId,
      content
    };

    axios.post('/api/messages', data)
      .then(response => {
        console.log(response);
        // clear the message input
        setContent('');
        // refresh the threads to display the new message
        axios.get(`/api/threads?userId=${userId}`)
          .then(response => {
            setThreads(response.data);
          })
          .catch(error => {
            console.log(error);
          });
      })
      .catch(error => {
        console.log(error);
      });
  };

  const createNewThread = () => {
    const data = {
      userId,
      partnerId: prompt('Enter the ID of the user you want to message:')
    };

    axios.post('/api/threads', data)
      .then(response => {
        console.log(response);
        // refresh the threads to display the new thread
        axios.get(`/api/threads?userId=${userId}`)
          .then(response => {
            setThreads(response.data);
            setSelectedThreadId(response.data[response.data.length - 1].id);
          })
          .catch(error => {
            console.log(error);
          });
      })
      .catch(error => {
        console.log(error);
      });
  };

  const terminateThread = () => {
    const data = {
      senderId: userId,
      threadId: selectedThreadId,
      content: 'Termination message'
    };

    axios.post('/api/messages', data)
      .then(response => {
        console.log(response);
        // refresh the threads to remove the terminated thread
        axios.get(`/api/threads?userId=${userId}`)
          .then(response => {
            setThreads(response.data);
            setSelectedThreadId(null);
          })
          .catch(error => {
            console.log(error);
          });
      })
      .catch(error => {
        console.log(error);
      });
  };

  return (
    <div>
      <h2>Messages</h2>
      <div>
        <button onClick={createNewThread}>New thread</button>
        <button onClick={terminateThread}>Terminate thread</button>
      </div>
      <div>
  <h3>Thread {selectedThreadId}</h3>
  {threads.find(thread => thread.id === selectedThreadId) ? (
    <ul>
      {threads.find(thread => thread.id === selectedThreadId).messages.map(message => (
        <li key={message.id}>{message.senderName}: {message.content}</li>
      ))}
    </ul>
  ) : (
    <p>No messages found</p>
  )}
  <input type="text" placeholder="Type your message here" value={content} onChange={e => setContent(e.target.value)} />
  <button onClick={sendMessage}>Send</button>
</div>
    </div>
  )
          }
  export default MessageScreen;