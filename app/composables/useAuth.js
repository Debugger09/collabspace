import { useCookie, useState } from '#app'

export const useAuth = () => {
  // Cookie pour le token JWT
  const token = useCookie('auth_token', { sameSite: 'lax' })
  // Cookie pour l'utilisateur (sérialisé automatiquement par Nuxt)
  const userCookie = useCookie('user_data', { sameSite: 'lax' })
  // State global pour l'utilisateur (initialisé depuis le cookie)
  const user = useState('user', () => userCookie.value || null)

  // Définir l'authentification
  const setAuth = (jwt, userData) => {
    token.value = jwt
    user.value = userData
    userCookie.value = userData
  }

  // Déconnexion
  const logout = () => {
    token.value = null
    user.value = null
    userCookie.value = null
  }

  return { user, token, setAuth, logout }
}