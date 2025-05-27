<template>
  <div>
    <!-- Header avec titre, recherche et bouton -->
    <div class="flex flex-col md:flex-row md:items-center md:justify-between mb-6 gap-4">
      <h1 class="text-2xl font-bold text-gray-800 md:order-1">{{ title }}</h1>
      <div class="flex-1 flex justify-center md:order-2">
        <input
          v-model="search"
          type="text"
          placeholder="Rechercher..."
          class="w-full max-w-xs border border-gray-300 rounded px-4 py-2 focus:ring-2 focus:ring-blue-500"
        />
      </div>
      <NuxtLink
        v-if="addUrl"
        :to="addUrl"
        class="flex items-center px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition duration-300 md:order-3"
      >
        <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
        </svg>
        {{ addLabel || 'Ajouter' }}
      </NuxtLink>
    </div>

    <!-- Tableau -->
    <div v-if="pending" class="text-center py-8">Chargement...</div>
    <div v-else-if="paginatedItems.length > 0" class="bg-white shadow-md rounded-lg overflow-hidden">
      <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
          <tr>
            <slot name="header" />
          </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr v-for="item in paginatedItems" :key="item.id" class="hover:bg-gray-50">
            <slot name="row" :item="item" />
          </tr>
        </tbody>
      </table>
      <!-- Pagination -->
      <div v-if="totalPages > 1" class="flex justify-center mt-6 gap-2">
        <button
          class="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300"
          :disabled="currentPage === 1"
          @click="currentPage--"
        >
          Précédent
        </button>
        <span class="px-2 py-1">{{ currentPage }} / {{ totalPages }}</span>
        <button
          class="px-3 py-1 rounded bg-gray-200 hover:bg-gray-300"
          :disabled="currentPage === totalPages"
          @click="currentPage++"
        >
          Suivant
        </button>
      </div>
    </div>
    <div v-else class="bg-white shadow-md rounded-lg p-8 text-center">
      <p class="text-gray-500">Aucun élément trouvé</p>
    </div>
    <div class="text-center text-xs text-gray-500 mt-2">
      {{ filteredItems.length }} utilisateur(s) au total
    </div>

    <!-- Modale de suppression -->
    <div v-if="showDeleteModal" class="fixed inset-0 flex justify-center items-center z-50 pointer-events-none">
      <div class="bg-white p-6 rounded-lg shadow-lg text-center pointer-events-auto">
        <h2 class="text-lg font-semibold mb-4">Confirmer la suppression</h2>
        <p>Êtes-vous sûr de vouloir supprimer cet élément ?</p>
        <div class="mt-6 flex justify-center gap-4">
          <button
            @click="deleteItem"
            class="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700"
          >
            Supprimer
          </button>
          <button
            @click="showDeleteModal = false"
            class="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
          >
            Annuler
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'

const props = defineProps({
  title: String,
  items: Array,
  pending: Boolean,
  addUrl: String,
  addLabel: String,
  onDelete: Function // callback pour supprimer
})

const emit = defineEmits(['delete'])

const search = ref('')
const currentPage = ref(1)
const pageSize = ref(10)
const showDeleteModal = ref(false)
const itemIdToDelete = ref(null)

const filteredItems = computed(() => {
  if (!props.items) return []
  if (!search.value) return props.items
  const s = search.value.toLowerCase()
  return props.items.filter(
    u =>
      (u.lastName && u.lastName.toLowerCase().includes(s)) ||
      (u.firstName && u.firstName.toLowerCase().includes(s)) ||
      (u.email && u.email.toLowerCase().includes(s)) ||
      (u.role && u.role.toLowerCase().includes(s))
  )
})

const totalPages = computed(() => Math.max(1, Math.ceil(filteredItems.value.length / pageSize.value)))
const paginatedItems = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  return filteredItems.value.slice(start, start + pageSize.value)
})

watch(filteredItems, () => { currentPage.value = 1 })

function confirmDelete(id) {
  itemIdToDelete.value = id
  showDeleteModal.value = true
}

async function deleteItem() {
  if (!itemIdToDelete.value) return
  showDeleteModal.value = false
  emit('delete', itemIdToDelete.value)
}
</script>