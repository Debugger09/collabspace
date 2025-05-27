import { useAuth } from '~/composables/useAuth'

export default defineNuxtRouteMiddleware((to, from) => {
  const { token } = useAuth()
  // Ne pas bloquer la page de login
  if (to.path === '/' || to.path === '/contact') return
  if (!token.value) {
    return navigateTo('/')
  }
})