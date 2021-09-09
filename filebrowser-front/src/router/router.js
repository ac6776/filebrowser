import Main from "@/pages/Main"
import About from "@/pages/About"
import { createRouter, createWebHistory } from "vue-router"

const routes = [
    { path: '/', component: Main },
    { path: '/about', component: About },
  ]

  const router = createRouter({
    history: createWebHistory(),
    routes, 
});

export default router;