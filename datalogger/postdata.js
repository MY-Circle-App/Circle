var http = require('http');
var querystring = require('querystring');

var request = require('request');
 

//console.log(postData);

postData = {
  "ID" : "smartBinMetal-01",
  "location" : "KLCC Hall 2",
  "Weight" : "",
  "material":""
};

var options = {
  hostname: 'api.parse.com',
  port: 80,
  path: '/1/classes/SmartBin',
  method: 'POST',
  headers: {
        'X-Parse-Application-Id': 'XzXTBKrA1rwteFCQShZ5kUGbPRhJTdwWDFEEeTlH',
  'X-Parse-REST-API-Key': 'NEmnVnUJtWFfqoBN2hHPQd9TKP2M1mixRrr0h2Bq',
    'Content-Type': 'application/json',
    'Content-Length': postData.length
  },
  json : true
};

console.log("Sending...");

/*
var req = http.request(options, function(res) {
//  console.log('STATUS: ' + res.statusCode);
//  console.log('HEADERS: ' + JSON.stringify(res.headers));
//  res.setEncoding('utf8');
  res.on('data', function (chunk) {
    console.log('BODY: ' + chunk);
  });
});

req.on('error', function(e) {
  console.log('problem with request: ' + e.message);
});
*/

// write data to request body
//req.write(postData);
//req.end();

request({

   url: "https://api.parse.com/1/classes/SmartBin",
   method: "POST",
   headers: {
      'X-Parse-Application-Id': 'XzXTBKrA1rwteFCQShZ5kUGbPRhJTdwWDFEEeTlH',
      'X-Parse-REST-API-Key': 'NEmnVnUJtWFfqoBN2hHPQd9TKP2M1mixRrr0h2Bq',
      'Content-Type': 'application/json',
   },
   body: postData,
   json: true
}, function(error, response, body){

   console.log(response);

});