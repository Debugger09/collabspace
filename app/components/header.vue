<template>
  <div class="flex h-screen">
    <!-- Sidebar -->
    <aside
      :class="['bg-blue-900 text-white w-64 space-y-6 py-7 px-2 fixed inset-y-0 left-0 transform transition-transform duration-300 z-50', sidebarOpen ? 'translate-x-0' : '-translate-x-full']"
    >
      <!-- Logo et nom -->
      <div class="flex items-center space-x-2 px-4">
        <img src="/assets/images/logo.png" alt="Logo" class="h-10 w-10" />
        <h1 class="text-2xl font-bold">
          <span class="text-white">Collab</span>
          <span class="text-blue-300">Space</span>
        </h1>
      </div>

      <!-- Liens de navigation -->
      <nav class="mt-10">
        <NuxtLink
          to="/dashboard"
          class="flex items-center py-2.5 px-4 rounded transition duration-200 hover:bg-blue-700"
          :class="route.path === '/dashboard' ? 'border-l-4 border-blue-300' : ''"
        >
          <svg class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
          </svg>
          Dashboard
        </NuxtLink>
        <!-- Nouveau lien Users -->
        <NuxtLink
          to="/users"
          class="flex items-center py-2.5 px-4 rounded transition duration-200 hover:bg-blue-700"
          :class="route.path.startsWith('/users') ? 'border-l-4 border-blue-300' : ''"
        >
          <svg class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
          </svg>
          Utilisateurs
        </NuxtLink>
        <NuxtLink
          to="/projects"
          class="flex items-center py-2.5 px-4 rounded transition duration-200 hover:bg-blue-700"
          :class="route.path.startsWith('/projects') ? 'border-l-4 border-blue-300' : ''"
        >
          <svg class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 17v-6a2 2 0 012-2h6a2 2 0 012 2v6m-2 4h-8a2 2 0 01-2-2v-6a2 2 0 012-2h8a2 2 0 012 2v6a2 2 0 01-2 2z" />
          </svg>
          Projets
        </NuxtLink>
        <NuxtLink
          to="/tasks"
          class="flex items-center py-2.5 px-4 rounded transition duration-200 hover:bg-blue-700"
          :class="route.path.startsWith('/tasks') ? 'border-l-4 border-blue-300' : ''"
        >
          <svg class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
          </svg>
          Tâches
        </NuxtLink>
        <NuxtLink
          to="/materials"
          class="flex items-center py-2.5 px-4 rounded transition duration-200 hover:bg-blue-700"
          :class="route.path.startsWith('/materials') ? 'border-l-4 border-blue-300' : ''"
        >
          <svg class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 7l-8-4-8 4m16 0l-8 4m8-4v10l-8 4m0-10L4 7m8 4v10M4 7v10l8 4" />
          </svg>
          Matériels
        </NuxtLink>
        <NuxtLink
          to="/expenses"
          class="flex items-center py-2.5 px-4 rounded transition duration-200 hover:bg-blue-700"
          :class="route.path.startsWith('/expenses') ? 'border-l-4 border-blue-300' : ''"
        >
          <svg class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          Dépenses
        </NuxtLink>
        <NuxtLink
          to="/messaging"
          class="flex items-center py-2.5 px-4 rounded transition duration-200 hover:bg-blue-700"
          :class="route.path.startsWith('/messaging') ? 'border-l-4 border-blue-300' : ''"
        >
          <svg class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 10h.01M12 10h.01M16 10h.01M9 16H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-5l-5 5v-5z" />
          </svg>
          Messagerie
        </NuxtLink>
        <NuxtLink
          to="/settings"
          class="flex items-center py-2.5 px-4 rounded transition duration-200 hover:bg-blue-700"
          :class="route.path.startsWith('/settings') ? 'border-l-4 border-blue-300' : ''"
        >
          <svg class="h-5 w-5 mr-3" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
          </svg>
          Paramètres
        </NuxtLink>
      </nav>
    </aside>

    <!-- Main content -->
    <div class="flex-1 flex flex-col" :style="{ marginLeft: sidebarOpen ? '16rem' : '0' }">
      <!-- Navbar -->
      <header class="bg-white shadow-md py-4 px-6 flex items-center justify-between">
        <div class="flex items-center space-x-4">
          <!-- Bouton toggle avant le logo -->
          <button @click="toggleSidebar" class="text-blue-900 focus:outline-none">
            <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </button>
          
          <!-- Logo qui disparaît quand la sidebar est ouverte -->
          <div v-if="!sidebarOpen" class="flex items-center space-x-2">
            <img src="/assets/images/logo.png" alt="Logo" class="h-8 w-8" />
            <h1 class="text-xl font-bold">
              <span class="text-blue-900">Collab</span>
              <span class="text-blue-600">Space</span>
            </h1>
          </div>
        </div>

        <!-- Bienvenue et nom de l'utilisateur -->
        <div class="text-lg font-medium text-gray-700">
          Bienvenu, <span class="text-blue-600">{{ userDisplayName }}</span>
        </div>

        <!-- Profil utilisateur -->
        <div class="relative">
          <div class="flex items-center space-x-2 cursor-pointer" @click="toggleDropdown">
            <div class="h-10 w-10 bg-blue-600 text-white rounded-full flex items-center justify-center font-bold">
              {{ userInitials }}
            </div>
            <svg class="h-5 w-5 text-gray-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
            </svg>
          </div>

          <!-- Dropdown menu avec icônes -->
          <div
            v-if="dropdownOpen"
            class="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-1 z-10"
          >
            <NuxtLink 
              to="/profile" 
              class="flex items-center px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
            >
              <svg class="h-4 w-4 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
              </svg>
              Profil
            </NuxtLink>
            <button 
              @click="handleLogout"
              class="flex items-center w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
            >
              <svg class="h-4 w-4 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
              </svg>
              Déconnexion
            </button>
          </div>
        </div>
      </header>

      <!-- Main content slot -->
      <main class="flex-1 p-6 bg-gray-50 overflow-y-auto">
        <slot />
      </main>

      <!-- Footer -->
      <footer class="bg-gray-100 text-center py-4 text-sm text-gray-600">
        © {{ new Date().getFullYear() }} CollabSpace. Tous droits réservés.
      </footer>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onBeforeUnmount } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

// État de la sidebar
const sidebarOpen = ref(false)
const toggleSidebar = () => {
  sidebarOpen.value = !sidebarOpen.value
}

// État du dropdown utilisateur
const dropdownOpen = ref(false)
const toggleDropdown = () => {
  dropdownOpen.value = !dropdownOpen.value
}

// Fermer le dropdown si on clique ailleurs
const closeDropdown = (event) => {
  if (!event.target.closest('.relative')) {
    dropdownOpen.value = false
  }
}

onMounted(() => {
  document.addEventListener('click', closeDropdown)
  if (window.innerWidth >= 768) {
    sidebarOpen.value = true
  }
})

onBeforeUnmount(() => {
  document.removeEventListener('click', closeDropdown)
})

// Gestion de l'authentification
const user = useState('user')
const tokenCookie = useCookie('auth_token')
const isAuthenticated = computed(() => !!tokenCookie.value)

// Affichage du nom utilisateur
const userDisplayName = computed(() => {
  if (!user.value) return 'Utilisateur'
  return [user.value.firstName, user.value.lastName].filter(Boolean).join(' ') || 'Utilisateur'
})

// Initiales de l'utilisateur
const userInitials = computed(() => {
  if (!user.value) return ''
  const firstName = user.value.firstName || ''
  const lastName = user.value.lastName || ''
  return `${firstName[0] || ''}${lastName[0] || ''}`.toUpperCase()
})

// Déconnexion
const handleLogout = async () => {
  const token = useCookie('auth_token')
  token.value = null
  user.value = null
  await navigateTo('/')
}
</script>