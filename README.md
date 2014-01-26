/game
  POST - Create a new game
  GET - Get your game list
/game/{game}
  GET - Get game info
  PUT - Apply (or start) a game
/game/{game}/words (?)
  GET - Get the game's word list
  PUT - Send a player's word list
  POST - Get the for a word list
/game/{game}/words/{player} (?)
  GET - Get a player's word list

/player
  POST - Create a new player
  GET - Get current player's profile
  PUT - Update player information
/player/{player}
  GET - Get a player's profile


Player
- id
- username

Game
- public
- state (created, started, finished)

WordQuery
- letter
- topic

WordResponse
- word
- unique
- score


GenericDAO - https://code.google.com/p/hibernate-generic-dao/
Jersey - https://jersey.java.net/

