package com.piyushprajpti.feature.auth.tenant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.domain.model.auth.Tenant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TenantViewModel : ViewModel() {
    private val _state = MutableStateFlow(TenantState())
    val state = _state.onStart {
        // load initial data here
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TenantState()
    )

    init {
        _state.update {
            it.copy(
                tenantList = dummyTenantList.sortedBy { tenant -> tenant.name }
            )
        }
    }

    fun onEvent(event: TenantEvent) {
        when (event) {
            is TenantEvent.OnContinueClick -> {}
            is TenantEvent.OnTenantChange -> onTenantChange(event.data)
            is TenantEvent.OnSearchQueryChange -> onSearchQueryChange(event.query)

        }
    }

    private fun onSearchQueryChange(query: String) {
        _state.update {
            it.copy(
                searchQuery = query,
                tenantList = if (query.isBlank()) {
                    dummyTenantList.sortedBy { tenant -> tenant.name }
                } else {
                    dummyTenantList.filter { tenant ->
                        tenant.name.contains(query, ignoreCase = true)
                    }.sortedBy { tenant -> tenant.name }
                }
            )
        }
    }

    private fun onTenantChange(data: Tenant) {
        _state.update {
            if (it.selectedTenant?.id == data.id) {
                it.copy(
                    selectedTenant = null,
                    searchQuery = ""
                )
            } else {
                it.copy(
                    selectedTenant = data,
                    searchQuery = data.name
                )
            }
        }
    }

    private fun setLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }

    }
}

val dummyTenantList = listOf(
    Tenant(
        id = "53eecd3e-5a1b-49fe-9263-666b482187c6",
        name = "Alpha University",
        slug = "alpha-university",
        domain = "www.alpha-uni.edu",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/284/small/colorful-star-logo-good-for-technology-logo-vintech-logo-company-logo-browser-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "123 Academic Way, Boston, MA",
        contactEmail = "admin@alpha-university.com",
        contactPhone = "+1-555-0101",
        createdAt = "2026-02-17T20:31:13.006Z",
        updatedAt = "2026-02-17T20:31:13.006Z"
    ),
    Tenant(
        id = "7726a11b-3c4d-4e5f-a6b7-889900aabbcc",
        name = "Beta Institute of Tech",
        slug = "beta-tech",
        domain = "www.betatech.edu",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/295/small_2x/modern-graphic-troly-colorful-logo-good-for-technology-logo-e-commerce-logo-online-shop-logo-company-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "456 Innovation Dr, San Francisco, CA",
        contactEmail = "info@betatech.edu",
        contactPhone = "+1-555-0202",
        createdAt = "2026-02-18T10:15:00.000Z",
        updatedAt = "2026-02-18T10:15:00.000Z"
    ),
    Tenant(
        id = "f47ac10b-58cc-4372-a567-0e02b2c3d479",
        name = "Gamma Medical College",
        slug = "gamma-medical",
        domain = "www.gammamed.org",
        logo = "",
        address = "789 Health St, Chicago, IL",
        contactEmail = "support@gammamed.org",
        contactPhone = "+1-555-0303",
        createdAt = "2026-02-19T08:45:22.000Z",
        updatedAt = "2026-02-19T08:45:22.000Z"
    ),
    Tenant(
        id = "d12e3456-e89b-12d3-a456-426614174000",
        name = "Delta Arts Academy",
        slug = "delta-arts",
        domain = "www.deltaarts.edu",
        logo = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/95965191-fd9b-43bd-b32a-1a00e55b0e02/d2pgs6a-8006bf26-2f11-4158-b9a1-d024688b8433.jpg",
        address = "101 Creative Blvd, New York, NY",
        contactEmail = "registrar@deltaarts.edu",
        contactPhone = "+1-555-0404",
        createdAt = "2026-02-20T12:00:00.000Z",
        updatedAt = "2026-02-20T12:00:00.000Z"
    ),
    Tenant(
        id = "b9c8d7e6-f5a4-3210-9876-543210fedcba",
        name = "Epsilon Business School",
        slug = "epsilon-biz",
        domain = "www.epsilonbiz.com",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/284/small/colorful-star-logo-good-for-technology-logo-vintech-logo-company-logo-browser-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "202 Commerce Ave, London, UK",
        contactEmail = "hello@epsilonbiz.com",
        contactPhone = "+44 20 7946 0123",
        createdAt = "2026-02-21T14:30:10.000Z",
        updatedAt = "2026-02-21T14:30:10.000Z"
    ),
    Tenant(
        id = "a1b2c3d4-e5f6-4789-8901-23456789abcd",
        name = "Zeta Polytechnic",
        slug = "zeta-poly",
        domain = "www.zetapoly.edu.in",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/295/small_2x/modern-graphic-troly-colorful-logo-good-for-technology-logo-e-commerce-logo-online-shop-logo-company-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "Sector 5, Salt Lake, Kolkata, India",
        contactEmail = "admin@zetapoly.in",
        contactPhone = "+91 98765 43210",
        createdAt = "2026-02-22T09:11:45.000Z",
        updatedAt = "2026-02-22T09:11:45.000Z"
    ),
    Tenant(
        id = "1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d",
        name = "Theta Engineering College",
        slug = "theta-engg",
        domain = "www.thetagroup.edu",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/019/073/790/small_2x/mw-monogram-mw-company-dummy-logo-mw-typography-vector.jpg",
        address = "303 Robot Rd, Austin, TX",
        contactEmail = "dean@thetagroup.edu",
        contactPhone = "+1-555-0707",
        createdAt = "2026-02-23T11:22:33.000Z",
        updatedAt = "2026-02-23T11:22:33.000Z"
    ),
    Tenant(
        id = "9a8b7c6d-5e4f-3g2h-1i0j-k9l8m7n6o5p4",
        name = "Iota Law Chambers",
        slug = "iota-law",
        domain = "www.iotalaw.com",
        logo = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/95965191-fd9b-43bd-b32a-1a00e55b0e02/d2pgs6a-8006bf26-2f11-4158-b9a1-d024688b8433.jpg",
        address = "50 Legal Ln, Toronto, Canada",
        contactEmail = "clerk@iotalaw.ca",
        contactPhone = "+1-555-0808",
        createdAt = "2026-02-24T16:45:00.000Z",
        updatedAt = "2026-02-24T16:45:00.000Z"
    ),
    Tenant(
        id = "2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e",
        name = "Kappa Science Center",
        slug = "kappa-science",
        domain = "www.kappascience.org",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/284/small/colorful-star-logo-good-for-technology-logo-vintech-logo-company-logo-browser-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "99 Lab Street, Berlin, Germany",
        contactEmail = "lab@kappa.de",
        contactPhone = "+49 30 123456",
        createdAt = "2026-02-25T07:30:15.000Z",
        updatedAt = "2026-02-25T07:30:15.000Z"
    ),
    Tenant(
        id = "3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f",
        name = "Lambda Music School",
        slug = "lambda-music",
        domain = "www.lambdamusic.fm",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/295/small_2x/modern-graphic-troly-colorful-logo-good-for-technology-logo-e-commerce-logo-online-shop-logo-company-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "12 Melody Lane, Nashville, TN",
        contactEmail = "play@lambda.fm",
        contactPhone = "+1-555-1010",
        createdAt = "2026-02-26T13:10:00.000Z",
        updatedAt = "2026-02-26T13:10:00.000Z"
    ),
    Tenant(
        id = "4d5e6f7a-8b9c-0d1e-2f3a-4b5c6d7e8f9a",
        name = "Mu Sports Academy",
        slug = "mu-sports",
        domain = "www.mu-sports.com",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/019/073/790/small_2x/mw-monogram-mw-company-dummy-logo-mw-typography-vector.jpg",
        address = "88 Stadium Way, Madrid, Spain",
        contactEmail = "coach@musports.es",
        contactPhone = "+34 91 123 4567",
        createdAt = "2026-02-27T10:00:00.000Z",
        updatedAt = "2026-02-27T10:00:00.000Z"
    ),
    Tenant(
        id = "5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b",
        name = "Nu Language Center",
        slug = "nu-languages",
        domain = "www.nulanguage.edu",
        logo = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/95965191-fd9b-43bd-b32a-1a00e55b0e02/d2pgs6a-8006bf26-2f11-4158-b9a1-d024688b8433.jpg",
        address = "55 Polyglot Ave, Paris, France",
        contactEmail = "info@nulanguage.fr",
        contactPhone = "+33 1 42 68 53 00",
        createdAt = "2026-02-28T09:20:00.000Z",
        updatedAt = "2026-02-28T09:20:00.000Z"
    ),
    Tenant(
        id = "6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c",
        name = "Xi Design Studio",
        slug = "xi-design",
        domain = "www.xi-designs.io",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/284/small/colorful-star-logo-good-for-technology-logo-vintech-logo-company-logo-browser-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "21 Pixel Path, Seattle, WA",
        contactEmail = "create@xistudio.io",
        contactPhone = "+1-555-1313",
        createdAt = "2026-03-01T15:15:00.000Z",
        updatedAt = "2026-03-01T15:15:00.000Z"
    ),
    Tenant(
        id = "7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d",
        name = "Omicron Culinary School",
        slug = "omicron-chef",
        domain = "www.omicronchef.com",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/295/small_2x/modern-graphic-troly-colorful-logo-good-for-technology-logo-e-commerce-logo-online-shop-logo-company-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "44 Gourmet Blvd, Rome, Italy",
        contactEmail = "chef@omicron.it",
        contactPhone = "+39 06 1234567",
        createdAt = "2026-03-02T11:40:00.000Z",
        updatedAt = "2026-03-02T11:40:00.000Z"
    ),
    Tenant(
        id = "8b9c0d1e-2f3a-4b5c-6d7e-8f9a0b1c2d3e",
        name = "Pi Aviation Academy",
        slug = "pi-aviation",
        domain = "www.piaviation.aero",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/019/073/790/small_2x/mw-monogram-mw-company-dummy-logo-mw-typography-vector.jpg",
        address = "Hangar 9, Dubai, UAE",
        contactEmail = "fly@pi-aviation.ae",
        contactPhone = "+971 4 123 4567",
        createdAt = "2026-03-03T08:00:00.000Z",
        updatedAt = "2026-03-03T08:00:00.000Z"
    ),
    Tenant(
        id = "9c0d1e2f-3a4b-5c6d-7e8f-9a0b1c2d3e4f",
        name = "Rho Robotics Lab",
        slug = "rho-robotics",
        domain = "www.rhorobotics.tech",
        logo = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/95965191-fd9b-43bd-b32a-1a00e55b0e02/d2pgs6a-8006bf26-2f11-4158-b9a1-d024688b8433.jpg",
        address = "7 Silicon Square, Tokyo, Japan",
        contactEmail = "service@rho.jp",
        contactPhone = "+81 3-1234-5678",
        createdAt = "2026-03-04T14:50:00.000Z",
        updatedAt = "2026-03-04T14:50:00.000Z"
    ),
    Tenant(
        id = "0d1e2f3a-4b5c-6d7e-8f9a-0b1c2d3e4f5a",
        name = "Sigma Theological Sem",
        slug = "sigma-sem",
        domain = "www.sigmasem.edu",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/284/small/colorful-star-logo-good-for-technology-logo-vintech-logo-company-logo-browser-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "3 Faith Plaza, Jerusalem, Israel",
        contactEmail = "dean@sigma.il",
        contactPhone = "+972 2-123-4567",
        createdAt = "2026-03-05T12:00:00.000Z",
        updatedAt = "2026-03-05T12:00:00.000Z"
    ),
    Tenant(
        id = "1e2f3a4b-5c6d-7e8f-9a0b-1c2d3e4f5a6b",
        name = "Tau Film School",
        slug = "tau-film",
        domain = "www.taufilm.com",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/011/883/295/small_2x/modern-graphic-troly-colorful-logo-good-for-technology-logo-e-commerce-logo-online-shop-logo-company-logo-dummy-logo-bussiness-logo-free-vector.jpg",
        address = "Sunset Blvd, Los Angeles, CA",
        contactEmail = "action@taufilm.com",
        contactPhone = "+1-555-1818",
        createdAt = "2026-03-06T10:30:00.000Z",
        updatedAt = "2026-03-06T10:30:00.000Z"
    ),
    Tenant(
        id = "2f3a4b5c-6d7e-8f9a-0b1c-2d3e4f5a6b7c",
        name = "Upsilon Maritime Acad",
        slug = "upsilon-maritime",
        domain = "www.upsilon-sea.org",
        logo = "https://static.vecteezy.com/system/resources/thumbnails/019/073/790/small_2x/mw-monogram-mw-company-dummy-logo-mw-typography-vector.jpg",
        address = "Pier 7, Singapore",
        contactEmail = "captain@upsilon.sg",
        contactPhone = "+65 6123 4567",
        createdAt = "2026-03-07T09:15:00.000Z",
        updatedAt = "2026-03-07T09:15:00.000Z"
    ),
    Tenant(
        id = "3a4b5c6d-7e8f-9a0b-1c2d-3e4f5a6b7c8d",
        name = "Phi Pharma Research",
        slug = "phi-pharma",
        domain = "www.phipharma.com",
        logo = "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/95965191-fd9b-43bd-b32a-1a00e55b0e02/d2pgs6a-8006bf26-2f11-4158-b9a1-d024688b8433.jpg",
        address = "1 Science Park, Zurich, Switzerland",
        contactEmail = "research@phi.ch",
        contactPhone = "+41 44 123 45 67",
        createdAt = "2026-03-08T16:00:00.000Z",
        updatedAt = "2026-03-08T16:00:00.000Z"
    )
)
