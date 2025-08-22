// Minimal loader to add React/ReactDOM UMD from CDN in the browser
(function(){
  if (!window.React || !window.ReactDOM) {
    var r = document.createElement('script'); r.src='https://unpkg.com/react@18/umd/react.production.min.js'; r.crossOrigin='';
    var d = document.createElement('script'); d.src='https://unpkg.com/react-dom@18/umd/react-dom.production.min.js'; d.crossOrigin='';
    document.head.appendChild(r); document.head.appendChild(d);
    r.onload = function(){ setTimeout(function(){ const App = () => React.createElement("div", {className:"hero"}, [\n  React.createElement("h1", {key:1}, "Aurora X"),\n  React.createElement("p", {key:2}, "Seed: 1337"),\n  React.createElement("p", {key:3}, "API: http://localhost:8080/api")\n]);\nReactDOM.createRoot(document.getElementById("root")).render(React.createElement(App));\n }, 50); };
  } else { const App = () => React.createElement("div", {className:"hero"}, [\n  React.createElement("h1", {key:1}, "Aurora X"),\n  React.createElement("p", {key:2}, "Seed: 1337"),\n  React.createElement("p", {key:3}, "API: http://localhost:8080/api")\n]);\nReactDOM.createRoot(document.getElementById("root")).render(React.createElement(App));\n }
})();
