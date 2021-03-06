import { useEffect, useState } from 'react';
import './App.css';

function App() {
  const [isGameStarted, setGameStarted] = useState(false);
  const [card, setCard] = useState({});
  const [seconds, setSeconds] = useState(10); // 1.
  const [isAnswered, setIsAnswered] = useState(false);
  const [response, setResponse] = useState(false);
  const [isTimeout, setTimeout] = useState(false);
  const timer = () => setSeconds(seconds - 1); // 2.
  
  function startGame() {
    fetch("http://localhost:8080/start-round")
      .then(res => res.json())
      .then(body => {
        setCard(body);
        setIsAnswered(false);
        setSeconds(10); // 3. 
        setGameStarted(true);
        setTimeout(false);
      })
  }

  // useEffect(() => {
  //   let seconds = 10;
  //   setSeconds(seconds);
  //   let timer = setInterval(() => {
  //       seconds--;
  //       setSeconds(seconds);
  //     if (seconds === 0) {
  //       clearInterval(timer);
  //       setTimeout(true);
  //     } else if (isAnswered === true) {
  //       clearInterval(timer);
  //     }
  //   },1000);
  // },[])

    useEffect(
        () => {
            if (seconds <= 0) {
                setTimeout(true);
                fetch("http://localhost:8080/timeout")
                return;
            }
            const id = setInterval(timer, 1000);
            return () => clearInterval(id);
        },
        [seconds]
    );  

  function sendNextCardGuess(guess) {
    fetch("http://localhost:8080/round-response/" + guess)
      .then(res => res.json())
      .then(body => {
        setIsAnswered(true);
        setResponse(body);
      })
  }
  
  return (
    <div className="App">
     {!isGameStarted && <button onClick={startGame}>ALUSTA MÄNGU</button>}
      <br />
     {isGameStarted && !isAnswered && 
     <div>
        <div>{seconds}</div>
        <br />
        <div>{card.rank} {card.suit}</div>
       { !isTimeout && <div>
          <div>Järgmine kaart on:</div>
          <button onClick={() => sendNextCardGuess("higher")}>SUUREM</button>
          <button onClick={() => sendNextCardGuess("lower")}>VÄIKSEM</button>
          <button onClick={() => sendNextCardGuess("equal")}>VÕRDNE</button>
        </div>}
       {isTimeout && <div>Sa ei jõudnud õigel ajal vastata!</div>}
      </div>}
      {response && isAnswered && <div>VASTASID ÕIGESTI</div>}
      {!response && isAnswered && <div>VASTASID VALESTI</div>}
      {(isAnswered || isTimeout) && <button onClick={startGame}>JÄRGMINE ARVAMINE</button>}
    </div>
  );
}

export default App;
