import Vue from 'vue/dist/vue.common'; // use Vue's runtime + compiler module
import App from './App.vue'
import VueRouter from 'vue-router'
import AddEntry from './components/AddEntry.vue'
import ViewGuestbook from './components/ViewGuestbook.vue'

export default function main({portletNamespace, contextPath, portletElementId}) {

  Vue.config.productionTip = false

  Vue.use(VueRouter)

  const router = new VueRouter({
      routes: [
          {
              path: "/",
              redirect: {
                  name: "ViewGuestbook"
              }
          },
          {
              path: '/view-guestbook',
              name: 'ViewGuestbook',
              component: ViewGuestbook
          },
          {
              path: '/add-entry',
              name: 'AddEntry',
              component: AddEntry
          }
      ]
  })
  new Vue({
    el: `#${portletElementId}`,
    render: h => h(App),
    router
  })
}