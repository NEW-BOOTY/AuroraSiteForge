const App = { data(){ return { title: "Aurora X", seed: "1337", api: "http://localhost:8080/api" } },
  template: `<div class="hero"><h1>{{ title }}</h1><p>Seed: {{ seed }}</p><p>API: {{ api }}</p></div>` };
Vue.createApp(App).mount('#app');
