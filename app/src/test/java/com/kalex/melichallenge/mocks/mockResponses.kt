package com.kalex.melichallenge.mocks

import com.kalex.melichallenge.search.model.dto.Paging
import com.kalex.melichallenge.search.model.dto.Result
import com.kalex.melichallenge.search.model.dto.SearchResponse
import com.kalex.melichallenge.search.model.dto.Sort

/**
 * @author kevin Alexander Soto on 6/2/2024
 * **/

val mockResult =  Result(
    accepts_mercadopago = true,
    attributes = listOf( ),
    available_quantity = 10,
    buying_mode = "buy",
    catalog_listing = false,
    catalog_product_id = "MLA12345678",
    category_id = "MLA1051",  // Replace with appropriate category ID
    condition = "new",
    currency_id = "ARS",
    differential_pricing = null,  // Optional
    discounts = Any(),  // Can be any type, replace with actual discount information if available
    domain_id = "MLA",
    id = "MLA98765432",
    installments = null,  // Optional
    inventory_id = null,  // Can be any type, set to null if not available
    listing_type_id = "gold_premium",
    official_store_id = 1,  // Can be any type, set to null if not available
    order_backend = 0,
    original_price = 10.0,  // Can be any type, set to null if not available
    permalink = "https://articulo.mercadolibre.com.ar/MLA12345678",
    price = 1234.56,
    promotions = listOf( /* List of promotions objects */ ),  // Optional
    sale_price = 12.00,  // Can be any type, set to null if not available
    seller = null,  // Replace with seller information
    shipping = null,  // Replace with shipping details
    site_id = "MLA",
    stop_time = "2024-06-03T00:00:00.000Z",
    thumbnail = "https://i.mlcdn.com.ar/600x600/articulo/MLA12345678.jpg",
    thumbnail_id = "MLA12345678-I",
    title = "Awesome Product Name",
    use_thumbnail_id = true,
    variation_filters = emptyList(),  // Initialize as empty list
    variation_id = "",
    variations_data = null,  // Can be null if not applicable
    winner_item_id = null  // Can be any type, set to null if not available
)
val mockSearchResponse = SearchResponse(
    available_sorts = listOf(),
    country_default_time_zone = "America/Argentina/Buenos_Aires",
    filters = listOf(),
    paging = Paging(total = 100, offset = 0, limit = 50, primary_results = 2),
    query = "smartphones",
    results = listOf(mockResult),
    site_id = "MLA",
    sort = Sort("relevance", "Default")
)