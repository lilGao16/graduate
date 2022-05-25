import Vue from 'vue';
import VueRouter from "vue-router";
import toSearch from "../views/toSearch";
import patient from "../views/patient";
import toUpload from "../views/toUpload";
import toDisplay from "../views/toDisplay";
import toSearch1 from "../views/toSearch1";
import add from "../views/add";
import change from "../views/change";
import disease from "../views/disease";
import change2 from "../views/change2";
import login from "../components/login";
import layout from "../views/layout";
import login2 from "../components/login2";
import users from "../views/user/users";
import adduser from "../views/user/adduser";
import changeuser from "../views/user/changeuser";

Vue.use(VueRouter);

export default new VueRouter({
  routes: [
    {
      path: '/toSearch',
      name: 'toSearch',
      component: toSearch
    },
    {
      path: '/toSearch1',
      name: 'toSearch1',
      component: toSearch1
    },
    {
      path: '/patient',
      name: 'patient',
      component: patient
    },
    {
      path: '/toUpload',
      name: 'toUpload',
      component: toUpload
    },
    {
      path: '/toDisplay',
      name: 'toDisplay',
      component: toDisplay
    },
    {
      path: '/add',
      name: 'add',
      component: add
    },
    {
      path: '/change',
      name: 'change',
      component: change
    },
    {
      path: '/disease',
      name: 'disease',
      component: disease
    },
    {
      path: '/change2',
      name: 'change2',
      component: change2
    },
    {
      path: '/login',
      name: 'login',
      component: login2
    },
    {
      path: '/',
      name: 'layout',
      component: layout
    },
    {
      path: '/users',
      name: 'users',
      component: users
    },
    {
      path: '/adduser',
      name: 'adduser',
      component: adduser
    },
    {
      path: '/changeuser',
      name: 'changeuser',
      component: changeuser
    },
  ]
});
