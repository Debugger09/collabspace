<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-50 to-blue-100">
    <div class="w-full max-w-md bg-white shadow-lg rounded-xl p-8">
      <!-- Logo et nom de l'application -->
      <div class="flex items-center justify-center mb-8">
        <img src="/assets/images/logo.png" alt="Logo" class="h-14 w-14 mr-4 drop-shadow-md" />
        <h1 class="text-3xl font-extrabold tracking-tight">
          <span class="text-blue-900">Collab</span>
          <span class="text-blue-600">Space</span>
        </h1>
      </div>

      <!-- Formulaire de connexion -->
      <form @submit.prevent="handleLogin">
        <!-- Champ Email -->
        <div class="mb-6 relative">
          <label for="email" class="block text-sm font-semibold text-gray-700">Adresse email</label>
          <div class="relative mt-2">
            <input
              v-model="email"
              type="email"
              id="email"
              name="email"
              autocomplete="email"
              class="block w-full px-4 py-3 pl-10 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 sm:text-sm transition duration-300"
              placeholder="Entrez votre email"
              required
            />
            <EnvelopeIcon class="absolute left-3 top-3 h-6 w-6 text-gray-400" />
          </div>
        </div>

        <!-- Champ Mot de passe -->
        <div class="mb-6 relative">
          <label for="password" class="block text-sm font-semibold text-gray-700">Mot de passe</label>
          <div class="relative mt-2">
            <input
              v-model="password"
              type="password"
              id="password"
              name="password"
              autocomplete="current-password"
              class="block w-full px-4 py-3 pl-10 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 sm:text-sm transition duration-300"
              placeholder="Entrez votre mot de passe"
              required
            />
            <LockClosedIcon class="absolute left-3 top-3 h-6 w-6 text-gray-400" />
          </div>
        </div>

        <!-- Affichage de l'erreur -->
        <div v-if="error" class="mb-4 text-red-600 text-sm text-center">
          {{ error }}
        </div>

        <!-- Bouton de connexion -->
        <div>
          <button
            type="submit"
            :disabled="isLoading"
            class="w-full flex justify-center py-3 px-4 border border-transparent rounded-lg shadow-md text-sm font-semibold text-white bg-gradient-to-r from-blue-600 to-blue-500 hover:from-blue-700 hover:to-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 transition duration-300 disabled:opacity-50 disabled:cursor-not-allowed"
          >
            <span v-if="!isLoading">Se connecter</span>
            <span v-else class="flex items-center">
              <svg class="animate-spin -ml-1 mr-2 h-4 w-4 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              Connexion en cours...
            </span>
          </button>
        </div>
      </form>

      <!-- Lien vers l'administrateur -->
      <p class="mt-8 text-center text-sm text-gray-600">
        Pas encore de compte ?
        <NuxtLink to="/contact" class="font-medium text-blue-600 hover:text-blue-500 transition duration-300">Contactez l'administrateur</NuxtLink>
      </p>
    </div>
  </div>
</template>

<script setup>
const email = ref('')
const password = ref('')
const isLoading = ref(false)
const error = ref(null)
const router = useRouter()

const { setAuth } = useAuth() // 👈 important

const handleLogin = async () => {
  isLoading.value = true
  error.value = null

  try {
    const response = await $fetch(`${useRuntimeConfig().public.apiBase}/auth/login`, {
      method: 'POST',
      body: {
        email: email.value,
        password: password.value
      }
    })

    // 👇 récupérer token + infos user
    const token = response.token
    const user = response.user

    // 👇 utiliser le composable
    setAuth(token, user)

    // ✅ rediriger
    router.push('/dashboard')
  } catch (err) {
    error.value = 'Email ou mot de passe incorrect'
    console.error(err)
  } finally {
    isLoading.value = false
  }
}
</script>
