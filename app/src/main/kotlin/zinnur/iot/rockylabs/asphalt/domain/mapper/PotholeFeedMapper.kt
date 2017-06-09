package zinnur.iot.rockylabs.asphalt.domain.mapper

import javax.inject.Inject
import rx.functions.Func1
import zinnur.iot.rockylabs.asphalt.data.entity.realm.PathData
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.domain.model.TrackModel


/**
 * Created by Zinnur on 04.06.17.
 */
class PotholeFeedMapper @Inject constructor() : Func1<List<PathData>, TrackModel> {

    override fun call(tracks: List<PathData>): TrackModel = TrackModel(tracks)

}