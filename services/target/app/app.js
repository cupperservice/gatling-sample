const express = require('express')
const bodyParser = require('body-parser')
require('date-utils')

const app = express()

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use(async (req, res, next) => {
  console.log(req.originalUrl)
  res.header("Cache-Control", "no-cache, no-store, must-revalidate");
  res.header("Pragma", "no-cache");
  res.header("Expires", 0);

next()
})

app.post('/users/login', async (req, res) => {
  console.log(req.body.login_id)
  console.log(req.body.password)
  let now = new Date()
  res.json({'token':`${now.toFormat('YYYYMMDDHH24MISS')}`})  
})

app.post('/users/logout', async (req, res) => {
  res.json({'result':'success'})
})

app.get('/items', async (req, res) => {
  console.log(`${req.get('Authorization')}`)
  const items = [
    {'id': 1, 'name': 'apple'},
    {'id': 2, 'name': 'orange'},
    {'id': 3, 'name': 'banana'},
    {'id': 4, 'name': 'strawberry'},
    {'id': 5, 'name': 'watermelon'}
  ]
  res.json(items)
})

app.get('/items/:item_id', async (req, res) => {
  console.log(req.params)
  res.json({'x':'y'})
})

app.post('/cart/items/:item_id', async (req, res) => {
  console.log(req.params.item_id)
  res.json({'x':'y'})
})

app.post('/cart/purchase', async (req, res) => {
  res.json({'result':'success'})
})

app.post('/cart/cancel', async (req, res) => {
  res.json({'result':'success'})
})

app.listen(3000, () => console.log('Example app listening on port 3000!'))
