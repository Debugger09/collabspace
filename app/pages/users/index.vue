<template>
  <Header>
    <Liste
      title="Gestion des Utilisateurs"
      :items="users"
      :pending="pending"
      add-url="/users/add"
      add-label="Ajouter un utilisateur"
      @delete="deleteUser"
    >
      <template #header>
        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nom</th>
        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Prénom</th>
        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Rôle</th>
        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Statut</th>
        <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
      </template>
      <template #row="{ item: user }">
        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ user.lastName }}</td>
        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ user.firstName }}</td>
        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ user.email }}</td>
        <td class="px-6 py-4 whitespace-nowrap">
          <span 
            :class="{
              'bg-purple-100 text-purple-800': user.role === 'ADMIN',
              'bg-blue-100 text-blue-800': user.role === 'CHEF_PROJET',
              'bg-green-100 text-green-800': user.role === 'MEMBRE_PROJET'
            }" 
            class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full"
          >
            {{ formatRole(user.role) }}
          </span>
        </td>
        <td class="px-6 py-4 whitespace-nowrap">
          <span 
            :class="user.status ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'" 
            class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full"
          >
            {{ user.status ? 'Actif' : 'Inactif' }}
          </span>
        </td>
        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
          <NuxtLink 
            :to="`/users/edit/${user.id}`" 
            class="text-blue-600 hover:text-blue-900 mr-4"
          >
            <svg class="w-5 h-5 inline" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
            </svg>
          </NuxtLink>
          <button 
            @click="$parent.confirmDelete(user.id)" 
            class="text-red-600 hover:text-red-900"
          >
            <svg class="w-5 h-5 inline" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
            </svg>
          </button>
        </td>
      </template>
    </Liste>
  </Header>
</template>

<script setup>
import Header from '~/components/header.vue'
import Liste from '~/components/liste.vue'
import { useRuntimeConfig } from '#app'

const config = useRuntimeConfig()

const { data: users, error, refresh, pending } = useFetch(
  () => `${config.public.apiBase}/user/read`,
  {
    immediate: true
  }
)

const formatRole = (role) => {
  const roles = {
    'ADMIN': 'Administrateur',
    'CHEF_PROJET': 'Chef de projet',
    'MEMBRE_PROJET': 'Membre'
  }
  return roles[role] || role
}

const deleteUser = async (id) => {
  try {
    await $fetch(`${config.public.apiBase}/user/${id}`, {
      method: 'DELETE'
    })
    await refresh()
  } catch (err) {
    console.error('Erreur suppression:', err)
  }
}
</script>