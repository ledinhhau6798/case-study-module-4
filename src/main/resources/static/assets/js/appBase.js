class AppBase {
    static DOMAIN_SERVER = location.origin
    static API_SERVER = this.DOMAIN_SERVER + '/api'

    static API_UPDATE_PRODUCT = this.API_PRODUCT + '/update'
    static API_DELETE_PRODUCT = this.API_PRODUCT + '/delete'
    static API_CART = this.API_SERVER + '/carts'
    static API_CATEGORY = this.API_SERVER + '/categories'
    static API_PRODUCT = this.API_SERVER + '/products'
    static IMAGE_SCALE_W_280_h_180_Q_100 = 'c_scale,w_280,h_180,q_100'
    static IMAGE_SCALE_W_80_h_60_Q_90 = 'c_scale,w_80,h_60,q_90'
    static DOMAIN_CLOUDINARY = 'https://res.cloudinary.com/toanphat/image/upload'
    static API_LOCATION_REGION = 'https://vapi.vnappmob.com/api/province'


    static SUCCESS_CREATED = "Successful data generation !";
    static SUCCESS_UPDATED = "Data update successful !";
    static SUCCESS_DELETED = "Delete product successfully !";

    static showDeleteConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to delete the selected product ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, please delete this client !',
            cancelButtonText: 'Cancel',
        })
    }

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }
}


class Product {
    constructor(id, title, price, quantity, unit, description, categoryTitle) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.description = description;
        this.categoryTitle = categoryTitle;
    }

}

class Avatar {
    constructor (fileId, fileName, fileFolder, fileUrl) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
    }
}